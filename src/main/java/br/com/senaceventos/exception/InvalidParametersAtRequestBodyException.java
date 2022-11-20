package br.com.senaceventos.exception;


public class InvalidParametersAtRequestBodyException extends RuntimeException{
    public InvalidParametersAtRequestBodyException(String message) {
        super(message);
    }
}
