package br.com.senaceventos.Exceptions;

import java.util.Date;

public record ErrorMessage(int httpStatus,
                           Date timestamp,
                           String message,
                           String description) {
}
