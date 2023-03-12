package br.com.erpsystem.mscliente.dto.http.response;

import br.com.erpsystem.mscliente.dto.ClienteDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteResponseDTO {

    @JsonProperty("cliente")
    private ClienteDTO clienteDTO;
}
