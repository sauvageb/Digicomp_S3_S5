package com.azqore.demo.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class TaskCommentsDTO extends TaskDto {

    private List<CommentDTO> comments;

}
