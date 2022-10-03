package br.com.senaceventos.Exceptions;


public class NoContentFoundAtCollectionException extends RuntimeException {
    public NoContentFoundAtCollectionException(String message) {
        super(message);
    }
}
