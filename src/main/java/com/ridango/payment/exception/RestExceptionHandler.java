package com.ridango.payment.exception;

import com.ridango.payment.model.Response;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(@NotNull MethodArgumentNotValidException ex) {
        return Response.nok(HttpStatus.UNPROCESSABLE_ENTITY, ex.getBody().getDetail());
    }
}
