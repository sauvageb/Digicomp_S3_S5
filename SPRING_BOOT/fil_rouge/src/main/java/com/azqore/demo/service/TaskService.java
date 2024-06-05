package com.azqore.demo.service;

import com.azqore.demo.entity.PriorityTask;
import com.azqore.demo.entity.Task;
import com.azqore.demo.entity.User;
import com.azqore.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // Permet au service d'etre injecté (Sous la forme d'un singleton)
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> fetchTaskList(){
        return (List<Task>) taskRepository.findAll();
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
}
