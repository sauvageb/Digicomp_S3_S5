package com.azqore.demo.api;

import com.azqore.demo.api.dto.CreateTask;
import com.azqore.demo.api.dto.ServerInfo;
import com.azqore.demo.api.dto.TaskDto;
import com.azqore.demo.api.dto.UserDto;
import com.azqore.demo.api.mapper.TaskMapper;
import com.azqore.demo.entity.Task;
import com.azqore.demo.entity.User;
import com.azqore.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor // Lombok genere le constructeur des champs en final
public class TaskRestController {

    private final TaskService taskService;

    @GetMapping("/version")
    public ServerInfo getVersion(){
        return new ServerInfo("1.0.0", "Demo App");
    }

    @GetMapping("/tasks")
    public List<TaskDto> getTaskList() {
        // Recuperer la liste des taches
        return taskService.fetchTaskList();
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
