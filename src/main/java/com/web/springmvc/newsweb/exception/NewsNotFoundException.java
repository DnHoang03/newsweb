package com.web.springmvc.newsweb.exception;

import java.io.Serial;

public class NewsNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public NewsNotFoundException(String message) {
        super(message);

    }
}
