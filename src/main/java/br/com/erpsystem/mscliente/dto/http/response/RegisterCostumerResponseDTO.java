package br.com.erpsystem.mscliente.dto.http.response;

import br.com.erpsystem.mscliente.dto.CustomerDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterCostumerResponseDTO {
    @JsonProperty("customer")
    private CustomerDTO costumerDTO;
}
