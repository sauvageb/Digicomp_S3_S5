package com.azqore.demo.api.mapper;

import com.azqore.demo.api.dto.TaskDto;
import com.azqore.demo.api.dto.UserDto;
import com.azqore.demo.entity.Task;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task fromDto(TaskDto dto) {
        return null;
    }

    public TaskDto toDto(Task taskEntity) {
        TaskDto dto = new TaskDto();
        dto.setId(taskEntity.getId());
        dto.setDescription(taskEntity.getDescription());
        dto.setDone(taskEntity.isDone());
        dto.setDueDate(taskEntity.getDueDate());
        dto.setPriority(taskEntity.getPriority());
        return dto;
    }

}
