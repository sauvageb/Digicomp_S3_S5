package com.azqore.demo.api.dto;

import com.azqore.demo.entity.PriorityTask;
import lombok.*;

import java.time.LocalDate;




@Data
@Builder
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String description;
    private PriorityTask priority;
    private LocalDate dueDate;
    private boolean isDone;
    private UserDto assignedUser;

    public TaskDto() {
    }

}
