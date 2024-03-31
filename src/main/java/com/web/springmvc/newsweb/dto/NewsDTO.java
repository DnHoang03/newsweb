package com.web.springmvc.newsweb.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsDTO {
    private String title;
    private String thumbnail;
    private String content;
    private String shortDescription;
    private Integer category;
    private Integer user;
}
