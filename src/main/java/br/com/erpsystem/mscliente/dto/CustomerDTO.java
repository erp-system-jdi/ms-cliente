package br.com.erpsystem.mscliente.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Size;
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
    @Size(min = 11, max = 11)
    @JsonProperty("cpf")
    private String cpf;
    @JsonProperty("address")
    private List<AddressDTO> addresses;
    @Size(min = 9, max = 9)
    @JsonProperty("rg")
    private String rg;
    @JsonProperty("phone")
    private String phone;


}
