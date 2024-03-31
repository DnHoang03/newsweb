package com.web.springmvc.newsweb.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    private Integer userId;
    private Integer newsId;
    private String content;
}
