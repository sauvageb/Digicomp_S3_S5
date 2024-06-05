package com.azqore.demo.api;


import com.azqore.demo.api.dto.TaskDto;
import com.azqore.demo.api.dto.UserDto;
import com.azqore.demo.entity.Task;
import com.azqore.demo.entity.User;
import com.azqore.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<?> fetchUsers(){
        List<User> userList = userService.fetchUsers();
        List<UserDto> userDtoList = new ArrayList<>();
        for(User u : userList){
            UserDto userDto = new UserDto();
            userDto.setId(u.getId());
            userDto.setFirstName(u.getFirstName());
            userDto.setLastName(u.getLastName());
            userDto.setEmail(u.getEmail());

            List<TaskDto> taskDtoList = new ArrayList<>();
            for(Task t : u.getTaskList()){
                TaskDto taskDto = new TaskDto();
                taskDto.setId(t.getId());
                taskDto.setDescription(t.getDescription());
                taskDto.setDueDate(t.getDueDate());
                taskDto.setPriority(t.getPriority());
                taskDto.setDone(t.isDone());
                taskDtoList.add(taskDto);
            }
            userDto.setTaskList(taskDtoList);
            userDtoList.add(userDto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(userDtoList);
    }
    



    
    // Ecrire des tests unitaires
    // Lancer les tests en lignes de commandes
    // Livrer un jar
    
    // Lombok
    // Spring security
    // Spring batch

}
