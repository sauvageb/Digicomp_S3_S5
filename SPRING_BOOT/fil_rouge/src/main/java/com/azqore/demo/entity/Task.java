package com.azqore.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {

    // Caracteristiques (Attributs)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @Enumerated(EnumType.STRING)
    private PriorityTask priority;

    private LocalDate dueDate;
    @Column(name = "done_status")
    private boolean isDone;

    @ManyToOne
    @JoinColumn(name = "user_fk")
    private User assignedUser;

    // Constructeurs
    public Task(String description, PriorityTask priority, LocalDate dueDate, User assignedUser) {
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.assignedUser = assignedUser;
        this.isDone = false;
    }

    public Task() {

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

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", isDone=" + isDone +
                ", assignedUser=" + assignedUser +
                '}';
    }
}
