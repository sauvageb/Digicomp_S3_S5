package com.azqore.demo.api;


import com.azqore.demo.api.dto.TaskDto;
import com.azqore.demo.api.dto.UserDto;
import com.azqore.demo.entity.Task;
import com.azqore.demo.entity.User;
import com.azqore.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor // Lombok constructeur (used for DI)
public class UserRestController {

    private final UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<?> fetchUsers() {
        List<User> userList = userService.fetchUsers();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User u : userList) {
            UserDto userDto = new UserDto();
            userDto.setId(u.getId());
            userDto.setFirstName(u.getFirstName());
            userDto.setLastName(u.getLastName());
            userDto.setEmail(u.getEmail());

            List<TaskDto> taskDtoList = new ArrayList<>();
            for (Task t : u.getTaskList()) {

                TaskDto taskDto = TaskDto
                        .builder()
                        .id(t.getId())
                        .description(t.getDescription())
                        .dueDate(t.getDueDate())
                        .priority(t.getPriority())
                        .isDone(t.isDone())
                        .build();

                taskDtoList.add(taskDto);
            }
            userDto.setTaskList(taskDtoList);
            userDtoList.add(userDto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(userDtoList);
    }
}
