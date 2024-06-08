package br.com.erpsystem.mscustomer.dto.http.request;

import br.com.erpsystem.mscustomer.dto.AddressDTO;
import br.com.erpsystem.mscustomer.dto.CustomerDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerUpdateRequestDTO {

    @NotEmpty
    @JsonProperty("last_name")
    private String lastName;
//    @JsonIgnore
//    private String fullName;

    @JsonProperty("email")
    @NotNull
    @Email(message = "Invalid email!")
    private String email;

    @NotNull
    @JsonProperty("phone")
    private String phone;
    //@JoinColumn(referencedColumnName = "id")
    @JsonProperty("addresses")
    private List<AddressDTO> addresses;

}
