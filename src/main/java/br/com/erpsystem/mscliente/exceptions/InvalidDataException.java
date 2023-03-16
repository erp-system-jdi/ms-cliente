package br.com.erpsystem.mscliente.exceptions;

import lombok.Data;

@Data
public class InvalidDataException extends RuntimeException {

    private static final long serialVersionUID = -1641049136523197632L;

    public InvalidDataException(String message) {
        super(message);
    }
}
