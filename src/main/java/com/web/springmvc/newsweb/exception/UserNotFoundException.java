package com.web.springmvc.newsweb.exception;

import java.io.Serial;

public class UserNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 2L;

    public UserNotFoundException(String message) {
        super(message);

    }
}
