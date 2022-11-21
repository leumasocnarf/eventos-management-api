package br.com.senaceventos.exceptions;


public class NoContentFoundAtCollectionException extends RuntimeException {
    public NoContentFoundAtCollectionException(String message) {
        super(message);
    }
}
