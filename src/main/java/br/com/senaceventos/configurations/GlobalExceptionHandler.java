package br.com.senaceventos.configurations;


import br.com.senaceventos.exceptions.ErrorMessage;
import br.com.senaceventos.exceptions.InvalidParametersAtRequestBodyException;
import br.com.senaceventos.exceptions.NoContentFoundAtCollectionException;
import br.com.senaceventos.exceptions.RegisterNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegisterNotFoundException.class)
    protected ResponseEntity<ErrorMessage> handleRegisterNotFound(RuntimeException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoContentFoundAtCollectionException.class)
    protected ResponseEntity<ErrorMessage> handleNoContentFoundAtCollectionException(RuntimeException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NO_CONTENT.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(InvalidParametersAtRequestBodyException.class)
    protected ResponseEntity<ErrorMessage> handleInvalidParametersAtRequestBodyException(RuntimeException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
