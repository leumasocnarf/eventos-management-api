package br.com.senaceventos.exceptions;


public class RegisterNotFoundException extends RuntimeException {
    public RegisterNotFoundException(String message) {
        super(message);
    }
}
