package br.com.senaceventos.exceptions;


public class InvalidParametersAtRequestBodyException extends RuntimeException{
    public InvalidParametersAtRequestBodyException(String message) {
        super(message);
    }
}
