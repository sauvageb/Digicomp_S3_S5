package com.azqore.demo.api.dto;

import java.time.LocalDateTime;

public record CommentDTO (Long id, String message, LocalDateTime createdAt, Long taskId) {}
