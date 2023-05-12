package br.com.erpsystem.mscustomer.exceptions;

public class DuplicatedCpfException extends RuntimeException {

    private final static long serialVersionUID = -5784765254741478438L;

    public DuplicatedCpfException(String message) {
        super(message);
    }
}
