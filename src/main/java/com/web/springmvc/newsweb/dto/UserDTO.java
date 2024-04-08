package com.web.springmvc.newsweb.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private int status;
    private String role;
}
