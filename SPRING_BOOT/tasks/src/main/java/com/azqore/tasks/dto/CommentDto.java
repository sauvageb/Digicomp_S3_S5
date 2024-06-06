package com.azqore.tasks.dto;

import java.time.LocalDateTime;

public record CommentDto (Long id, String message, LocalDateTime createdAt,Long taskId) {}
