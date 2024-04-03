package com.ivan.passwordmanager.handlers;

import com.ivan.passwordmanager.dto.exceptionDto.ErrorMessageDto;
import com.ivan.passwordmanager.exeptions.NotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = NotFound.class)
    @ResponseBody
    public ResponseEntity<ErrorMessageDto> handler(NotFound ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErrorMessageDto(ex.getMessage()));
    }
}
