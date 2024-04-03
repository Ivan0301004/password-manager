package com.ivan.passwordmanager.exeptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFound extends RuntimeException {

    private final HttpStatus status;

    public NotFound(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
