package br.com.erpsystem.mscliente.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDTO {
    private UUID id;
    @NotEmpty
    @JsonProperty("first_name")
    private String firstName;
    @NotEmpty
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("email")
    @NotNull
    @Email(message = "Invalid email!")
    private String email;
    @Past(message = "birthdate must be in the past!")
    @JsonProperty("birthdate")
    private LocalDate birthdate;
    @NotNull
    @Size(min = 11, max = 11, message = "Invalid CPF!")
    @JsonProperty("cpf")
    private String cpf;
    @NotNull
    @Size(min = 9, max = 9, message = "Invalid RG!")
    @JsonProperty("rg")
    private String rg;
    @NotNull
    @JsonProperty("phone")
    private String phone;
    @JoinColumn(referencedColumnName = "id")
    @JsonProperty("address")
    private List<AddressDTO> addresses;
    @JsonIgnore
    private LocalDateTime registerDate;


}
