package com.azqore.tasks.batch;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ImportCommentDto (String content, String date, String taskId){}
