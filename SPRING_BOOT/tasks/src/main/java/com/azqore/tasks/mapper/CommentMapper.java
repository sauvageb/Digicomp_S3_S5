package com.azqore.tasks.mapper;

import com.azqore.tasks.dto.CommentDto;
import com.azqore.tasks.repository.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper implements Mapper<Comment, CommentDto> {


    @Override
    public CommentDto toDto(Comment c) {
        return new CommentDto(c.getId(), c.getMessage(), c.getCreatedAt(), c.getTaskId());
    }

    @Override
    public Comment fromDto(CommentDto d) {
        return Comment
                .builder()
                .id(d.id())
                .message(d.message())
                .taskId(d.taskId())
                .createdAt(d.createdAt())
                .build();
    }
}
