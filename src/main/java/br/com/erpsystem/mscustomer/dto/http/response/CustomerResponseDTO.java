package br.com.erpsystem.mscustomer.dto.http.response;

import br.com.erpsystem.mscustomer.dto.CustomerDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDTO {

    @JsonProperty("customer")
    private CustomerDTO customerDTO;
}
