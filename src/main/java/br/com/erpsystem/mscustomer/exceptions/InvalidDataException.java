package br.com.erpsystem.mscustomer.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InvalidDataException extends RuntimeException {

    private static final long serialVersionUID = -1641049136523197632L;

    private ExceptionResponse exceptionResponse;

    public InvalidDataException(ExceptionResponse e) {
        super();
        this.exceptionResponse = e;
    }
}
