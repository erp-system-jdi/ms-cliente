package br.com.erpsystem.mscustomer.dto.http.request;

import br.com.erpsystem.mscustomer.dto.CustomerDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterCostumerRequestDTO {
    @JsonProperty("customer")
    private CustomerDTO customerDTO;
}
