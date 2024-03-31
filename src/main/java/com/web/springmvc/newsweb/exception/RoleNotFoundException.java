package com.web.springmvc.newsweb.exception;

import java.io.Serial;

public class RoleNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 4L;
    public RoleNotFoundException(String message) {
        super(message);
    }
}
