package br.com.erpsystem.mscliente.exceptions;

import br.com.erpsystem.mscliente.enums.ErrorCodes;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = -336474221953947561L;

    private final String code;
    private final String message;
    private final List<String> details;

    public ExceptionResponse(final ErrorCodes errorCode, String details) {
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
        this.details = Collections.singletonList(details);
    }

    public ExceptionResponse(ErrorCodes errorCode, List<String> details) {
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
        this.details = details;
    }

}
