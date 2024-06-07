package com.azqore.tasks.batch;

import com.azqore.tasks.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class CommentImportBatch {

    private final CommentBatchProcessor commentBatchProcessor;
    private final CommentBatchWriter commentBatchWriter;
    private final CommentBatchReader commentBatchReader;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    // Job
    @Bean
    Job commentImportJob(){
        return new JobBuilder("commentImportJob", jobRepository)
                .start(importCommentStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    // Step
    @Bean
    Step importCommentStep(){
        return new StepBuilder("importCommentStep", jobRepository)
                .<ImportCommentDto, CommentDto>chunk(2, transactionManager)
                .reader(commentBatchReader) // Reader CSV
                .processor(commentBatchProcessor) // Processor
                .writer(commentBatchWriter) // Writer BDD
                .build();
    }








}
