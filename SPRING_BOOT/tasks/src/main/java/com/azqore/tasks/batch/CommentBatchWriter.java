package com.azqore.tasks.batch;

import com.azqore.tasks.dto.CommentDto;
import com.azqore.tasks.service.CommentService;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentBatchWriter implements ItemWriter<CommentDto> {

    private final CommentService commentService;

    public CommentBatchWriter(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public void write(Chunk<? extends CommentDto> chunk) throws Exception {
        for (CommentDto data : chunk.getItems()){
            commentService.addComment(data);
        }

    }
}
