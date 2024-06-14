package br.com.erpsystem.mscustomer.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerNotFoundException extends RuntimeException{

    private static final long serialVersionUID = -7673488275591493173L;

    private ExceptionResponse exceptionResponse;

    public CustomerNotFoundException(ExceptionResponse e) {
        super();
        this.exceptionResponse = e;
    }
}
