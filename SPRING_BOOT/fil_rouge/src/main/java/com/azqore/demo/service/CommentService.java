package com.azqore.demo.service;

import com.azqore.demo.api.dto.CommentDTO;
import com.azqore.demo.config.WebServiceConfig;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final WebServiceConfig webServiceConfig;
    private final RestTemplate restTemplate;

    public List<CommentDTO> fetchTaskComments(Long taskId) {
        List<CommentDTO> commentDTOS = new ArrayList<>();
        CommentDTO[] comments = restTemplate.getForObject(webServiceConfig.getCommentsUrl(), CommentDTO[].class);
        if (comments != null) {
            commentDTOS = Stream.of(comments).filter(c -> c.taskId().equals(taskId)).toList();
        }
        return commentDTOS;
    }
}
