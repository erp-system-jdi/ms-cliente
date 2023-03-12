package br.com.erpsystem.mscliente.controllers;

import br.com.erpsystem.mscliente.dto.ClienteDTO;
import br.com.erpsystem.mscliente.dto.http.response.ClienteResponseDTO;
import br.com.erpsystem.mscliente.services.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDTO> buscarClientePorCpf(@PathVariable("cpf") String cpf){
        log.info("ClienteController.buscarClientePorCpf - Start - cpf: {}", cpf);
        ClienteDTO clienteDTO = clienteService.buscarClientePorCpf(cpf);
        log.info("ClienteController.buscarClientePorCpf - End");
        return ResponseEntity.ok(clienteDTO);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteResponseDTO> cadastrarCliente(@RequestBody ClienteDTO clienteDTO){
        log.info("ClienteController.cadastrarCliente - Start - clienteDTO: {}", clienteDTO);

        ClienteResponseDTO clienteResponseDTO = ClienteResponseDTO.builder()
                .clienteDTO(clienteService.salvarCliente(clienteDTO))
                .build();

        log.info("ClienteController.cadastrarCliente - End");

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponseDTO);
    }
}
