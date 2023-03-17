package br.com.erpsystem.mscliente.dto.http.request;

import br.com.erpsystem.mscliente.dto.CustomerDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterCostumerRequestDTO {
    @JsonProperty("customer")
    private CustomerDTO customerDTO;
}
