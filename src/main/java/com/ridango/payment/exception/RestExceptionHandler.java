package com.ridango.payment.exception;

import com.ridango.payment.model.Response;
import jakarta.transaction.Transactional;
import jakarta.transaction.TransactionalException;
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

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(@NotNull NotFoundException ex) {
        return Response.nok(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(@NotNull IllegalArgumentException ex) {
        return Response.nok(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleTransactionalException(@NotNull RuntimeException ex) {
        return Response.nok(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
}
