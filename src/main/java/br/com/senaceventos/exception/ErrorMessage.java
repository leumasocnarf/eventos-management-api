package br.com.senaceventos.exception;

import java.util.Date;

public record ErrorMessage(int httpStatus,
                           Date timestamp,
                           String message,
                           String description) {
}
