package com.azqore.tasks.batch;

import com.opencsv.CSVReader;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Component
public class CommentBatchReader implements ItemReader<ImportCommentDto>, StepExecutionListener {

    private CSVReader csvReader;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        FileSystemResource resource = new FileSystemResource("comments.csv");

        try {
            FileReader fileReader = new FileReader(resource.getFile());
            csvReader = new CSVReader(fileReader);
        }catch (FileNotFoundException e){
            System.err.println("Fichier introuvable");
        }
    }

    @Override
    public ImportCommentDto read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        try {
        String[] currentLine = csvReader.readNext();
        if(currentLine != null){
            return ImportCommentDto
                    .builder()
                    .content(currentLine[0])
                    .date(currentLine[1])
                    .taskId(currentLine[2])
                    .build();
        }
        }catch (ValidationException e){
            System.err.println("Validation CSV");
        }catch (IOException e){
            System.err.println("I/O Exception");
        }

        // End of file : return null when last line is empty
        return null;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        try {
            if (csvReader != null) {
                csvReader.close();
            }
        }catch (IOException e){
            System.err.println("Closing CSV Reader");
        }
        return ExitStatus.COMPLETED;
    }
}
