package com.looyt.usermanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.looyt.usermanagement.model.dto.response.BaseResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler 
{
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public BaseResponse<Void> handleUserNotFoundException(UserNotFoundException exception)
    {
        String message = exception.getMessage(); 

        log.warn(message);

        return BaseResponse.<Void>builder()
            .status(HttpStatus.NOT_FOUND.value())
            .message(message)
            .build(); 
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<Void> handleOtherExceptions(Exception exception)
    {
        String message = exception.getMessage(); 

        log.error(message);
        
        return BaseResponse.<Void>builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message(message)
            .build(); 
    }
}
