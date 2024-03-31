package com.web.springmvc.newsweb.exception;

import java.io.Serial;

public class CategoryNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 3L;

    public CategoryNotFoundException(String message) {
        super(message);

    }
}
