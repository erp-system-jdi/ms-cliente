package br.com.erpsystem.mscustomer.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidUpdateException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private ExceptionResponse exceptionResponse;

    public InvalidUpdateException(ExceptionResponse e){
        super();
        this.exceptionResponse = e;
    }
}
