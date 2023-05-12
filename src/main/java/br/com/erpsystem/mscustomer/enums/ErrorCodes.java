package br.com.erpsystem.mscustomer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {

    INTERNAL_SERVER_ERROR("Internal server error"),
    INVALID_REQUEST("Invalid request"),
    VALIDATION_FAILED("Validation failed"),
    CUSTOMER_NOT_FOUND("Customer not found"),
    BAD_REQUEST("Bad Request"),
    MALFORMED_JSON("Malformed JSON"),
    RESOURCE_NOT_FOUND("Resource not found"),
    INVALID_DATA("Invalid Data"),
    DUPLICATED_CPF("Duplicated CPF");

    private final String message;

}