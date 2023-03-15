package br.com.erpsystem.mscliente.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDTO {
    private UUID id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("birthdate")
    private Date birthdate;
    @JsonProperty("cpf")
    private String cpf;
    @JsonProperty("address")
    private List<AddressDTO> addresses;
    @JsonProperty("rg")
    private String rg;
    @JsonProperty("phone")
    private String phone;


}
