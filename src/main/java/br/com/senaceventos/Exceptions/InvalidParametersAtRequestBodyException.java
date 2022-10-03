package br.com.senaceventos.Exceptions;


public class InvalidParametersAtRequestBodyException extends RuntimeException{
    public InvalidParametersAtRequestBodyException(String message) {
        super(message);
    }
}
