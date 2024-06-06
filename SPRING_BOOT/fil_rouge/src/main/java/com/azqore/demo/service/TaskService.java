package com.azqore.demo.service;

import com.azqore.demo.api.dto.CommentDTO;
import com.azqore.demo.api.dto.TaskCommentsDTO;
import com.azqore.demo.api.dto.TaskDto;
import com.azqore.demo.api.mapper.TaskMapper;
import com.azqore.demo.api.mapper.UserMapper;
import com.azqore.demo.entity.PriorityTask;
import com.azqore.demo.entity.Task;
import com.azqore.demo.entity.User;
import com.azqore.demo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service // Permet au service d'etre injecté (Sous la forme d'un singleton)
@RequiredArgsConstructor // Lombok genere un constructeur avec les champs final
public class TaskService {

    private final TaskMapper taskMapper;
    private final UserMapper userMapper;
    private final TaskRepository taskRepository;
    private final RestTemplate restTemplate;

    public List<TaskDto> fetchTaskList(){
        List<Task> tasks = (List<Task>) taskRepository.findAll();
        // VERSION 1 : stream
        List<TaskDto> dtos = tasks.stream().map(task -> taskMapper.toDto(task)).toList();
        // VERSION 2 : java 7
        dtos = new ArrayList<>();
        for (Task t : tasks){
            TaskDto taskDto = taskMapper.toDto(t);
            taskDto.setAssignedUser(userMapper.toDto(t.getAssignedUser()));
            dtos.add(taskDto);
        }
        return dtos;
    }

    public void addTask(Task taskToCreate){
        taskRepository.save(taskToCreate);
    }

    public void deleteSpecificTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void toggleStatus(Long idTask) {
        Optional<Task> taskOpt = taskRepository.findById(idTask);
        if(taskOpt.isPresent()){
            Task task = taskOpt.get();
            task.setDone(!task.isDone());
            taskRepository.save(task);
        }
    }

    public TaskCommentsDTO fetchTaskWithComments(Long taskId) {
            Optional<Task> taskOpt = taskRepository.findById(taskId);

            if(taskOpt.isPresent()) {
               TaskDto taskDto = taskMapper.toDto(taskOpt.get());

                String url = "http://localhost:9191/api/comments";
                CommentDTO[] comments = restTemplate.getForObject(url, CommentDTO[].class);
                List<CommentDTO> commentDTOS = new ArrayList<>();
                if(comments != null){
                   commentDTOS = Stream.of(comments).filter(c -> c.taskId().equals(taskId)).toList();
                }

                TaskCommentsDTO dto = new TaskCommentsDTO();
                dto.setId(taskDto.getId());
                dto.setDueDate(taskDto.getDueDate());
                dto.setDescription(taskDto.getDescription());
                dto.setPriority(taskDto.getPriority());
                dto.setAssignedUser(taskDto.getAssignedUser());
                dto.setComments(commentDTOS);
                return dto;
            }
            throw new RuntimeException("Exception while fetching comments");
    }

}
