package com.azqore.tasks.batch;

import com.azqore.tasks.dto.CommentDto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentBatchProcessor implements ItemProcessor<ImportCommentDto, CommentDto> {

    @Override
    public CommentDto process(ImportCommentDto item) throws Exception {
        // Traitement : CommentImportBatch ==> CommentDto
        if(item.content().contains("forbidden keyword")){
            //..
        }
        return CommentDto
                .builder()
                .message(item.content())
                .createdAt(LocalDateTime.parse(item.date()))
                .taskId(Long.valueOf(item.taskId()))
                .build();
    }


}
