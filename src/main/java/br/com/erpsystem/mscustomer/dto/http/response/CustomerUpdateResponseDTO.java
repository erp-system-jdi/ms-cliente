package br.com.erpsystem.mscustomer.dto.http.response;

import br.com.erpsystem.mscustomer.dto.CustomerDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerUpdateResponseDTO {
    @JsonProperty("customer")
    private CustomerDTO customerDTO;
}
