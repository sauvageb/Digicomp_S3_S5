package com.azqore.tasks.controller;

import com.azqore.tasks.dto.CommentDto;
import com.azqore.tasks.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comments")
    public ResponseEntity<?> fetchComments(){
        List<CommentDto> data = commentService.fetchComments();
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}
