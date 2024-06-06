package com.azqore.demo.api.mapper;

import com.azqore.demo.api.dto.TaskCommentsDTO;
import com.azqore.demo.api.dto.TaskDto;
import com.azqore.demo.api.dto.UserDto;
import com.azqore.demo.entity.Task;
import com.azqore.demo.entity.User;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskMapper {

    private final UserMapper userMapper;

    public TaskDto toDto(Task taskEntity) {
        TaskDto dto = new TaskDto();
        dto.setId(taskEntity.getId());
        dto.setDescription(taskEntity.getDescription());
        dto.setDone(taskEntity.isDone());
        dto.setDueDate(taskEntity.getDueDate());
        dto.setPriority(taskEntity.getPriority());

        User user = taskEntity.getAssignedUser();
        UserDto userDto = userMapper.toDto(user);
        dto.setAssignedUser(userDto);

        return dto;
    }

    public Task fromDto(TaskDto dto) {
        return null;
    }

}
