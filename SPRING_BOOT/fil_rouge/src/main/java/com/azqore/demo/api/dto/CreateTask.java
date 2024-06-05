package com.azqore.demo.api.dto;

import com.azqore.demo.entity.PriorityTask;
import jakarta.persistence.*;

import java.time.LocalDate;

public class CreateTask {

    private String description;
    private PriorityTask priority;
    private LocalDate dueDate;
    private Long assignedUserId;

    public CreateTask() {
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

    public Long getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(Long assignedUserId) {
        this.assignedUserId = assignedUserId;
    }
}
