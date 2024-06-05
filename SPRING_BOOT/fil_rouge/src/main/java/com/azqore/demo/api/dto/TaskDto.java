package com.azqore.demo.api.dto;

import com.azqore.demo.entity.PriorityTask;

import java.time.LocalDate;

public class TaskDto {
    private Long id;
    private String description;
    private PriorityTask priority;
    private LocalDate dueDate;
    private boolean isDone;
    private UserDto assignedUser;

    public TaskDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PriorityTask getPriority() {
        return priority;
    }

    public void setPriority(PriorityTask priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public UserDto getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(UserDto assignedUser) {
        this.assignedUser = assignedUser;
    }
}
