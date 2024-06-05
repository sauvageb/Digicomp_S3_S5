package com.azqore.demo.api;

import com.azqore.demo.api.dto.CreateTask;
import com.azqore.demo.api.dto.ServerInfo;
import com.azqore.demo.api.dto.TaskDto;
import com.azqore.demo.api.dto.UserDto;
import com.azqore.demo.entity.Task;
import com.azqore.demo.entity.User;
import com.azqore.demo.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class TaskRestController {

    private final TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/version")
    public ServerInfo getVersion(){
        return new ServerInfo("1.0.0", "Demo App");
    }

    @GetMapping("/tasks")
    public List<TaskDto> getTaskList() {
        // Recuperer la liste des taches
        List<Task> taskList = taskService.fetchTaskList();
        // Convertir la liste des taches en liste de dto de taches
        // VERSION 1 : AVEC LES STREAMS
        List<TaskDto> taskListDto = taskList
                .stream()
                .map(taskEntity -> {
                    TaskDto dto = new TaskDto();
                    dto.setId(taskEntity.getId());
                    dto.setDescription(taskEntity.getDescription());
                    dto.setDone(taskEntity.isDone());
                    dto.setDueDate(taskEntity.getDueDate());
                    dto.setPriority(taskEntity.getPriority());

                    UserDto userDto = new UserDto();
                    userDto.setId(taskEntity.getAssignedUser().getId());
                    userDto.setFirstName(taskEntity.getAssignedUser().getFirstName());
                    userDto.setLastName(taskEntity.getAssignedUser().getLastName());
                    userDto.setEmail(taskEntity.getAssignedUser().getEmail());
                    dto.setAssignedUser(userDto);
                    return dto;
                })
                .collect(Collectors.toList());

        // VERSION 2 : SANS LES STREAMS, AVEC UNE BOUCLE
        taskListDto = new ArrayList<>();
        for (Task taskEntity : taskList) {
            TaskDto dto = new TaskDto();
            dto.setId(taskEntity.getId());
            dto.setDescription(taskEntity.getDescription());
            dto.setDone(taskEntity.isDone());
            dto.setDueDate(taskEntity.getDueDate());
            dto.setPriority(taskEntity.getPriority());
            UserDto userDto = new UserDto();
            userDto.setId(taskEntity.getAssignedUser().getId());
            userDto.setFirstName(taskEntity.getAssignedUser().getFirstName());
            userDto.setLastName(taskEntity.getAssignedUser().getLastName());
            userDto.setEmail(taskEntity.getAssignedUser().getEmail());
            dto.setAssignedUser(userDto);
            taskListDto.add(dto);
        }

        // retourner la liste
        return taskListDto;
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> createTask(@RequestBody CreateTask dto){

        Task newTask = new Task();
        newTask.setDescription(dto.getDescription());
        newTask.setPriority(dto.getPriority());
        newTask.setDueDate(dto.getDueDate());
        newTask.setDone(false);

        User user = new User();
        user.setId(dto.getAssignedUserId());
        newTask.setAssignedUser(user);
        taskService.addTask(newTask);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
