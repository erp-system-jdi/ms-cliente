package br.com.erpsystem.mscustomer.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BusinessErrorConstants {

    public static final String CUSTOMER_NOT_FOUND = "[Business Exception] Customer not Found!";

    public static final String DUPLICATED_CPF = "[Business Exception] This CPF already exists in the database!";

    public static final String CPF_RG_NOT_UPDATE = "[Business Exception] CPF and RG cannot be changed!";

    public static final String INVALID_UPDATE = "[Business Exception] Please fill in the fields to update customer data!";
    public static final String CUSTOMER_ID_NULL = "[Business Exception] CustomerId is empty!";
}
