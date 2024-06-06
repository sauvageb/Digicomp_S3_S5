package com.azqore.tasks.service;

import com.azqore.tasks.dto.CommentDto;
import com.azqore.tasks.mapper.CommentMapper;
import com.azqore.tasks.repository.CommentRepository;
import com.azqore.tasks.repository.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public List<CommentDto> fetchComments(){

        List<Comment> commentList = (List<Comment>) commentRepository.findAll();

        return commentList
                .stream()
                .map(c -> commentMapper.toDto(c))
                .toList();
    }

}
