package com.web.springmvc.newsweb.exception;

import java.io.Serial;

public class UserAlreadyExistsException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 5L;

    public UserAlreadyExistsException(String message) {
        super(message);

    }
}
