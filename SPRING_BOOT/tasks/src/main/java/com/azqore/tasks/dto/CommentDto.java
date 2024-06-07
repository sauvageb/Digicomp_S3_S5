package com.azqore.tasks.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentDto (Long id, String message, LocalDateTime createdAt, Long taskId) {}
