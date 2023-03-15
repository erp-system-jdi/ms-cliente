package br.com.erpsystem.mscliente.controllers;

import br.com.erpsystem.mscliente.dto.CustomerDTO;
import br.com.erpsystem.mscliente.dto.http.response.CustomerResponseDTO;
import br.com.erpsystem.mscliente.services.CustomerService;
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
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> buscarClientePorCpf(@PathVariable("cpf") String cpf){
        log.info("ClienteController.buscarClientePorCpf - Start - cpf: {}", cpf);
        CustomerDTO customerDTO = customerService.findCustomerByCpf(cpf);
        log.info("ClienteController.buscarClientePorCpf - End");
        return ResponseEntity.ok(customerDTO);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponseDTO> cadastrarCliente(@RequestBody CustomerDTO customerDTO){
        log.info("ClienteController.cadastrarCliente - Start - clienteDTO: {}", customerDTO);

        CustomerResponseDTO customerResponseDTO = CustomerResponseDTO.builder()
                .customerDTO(customerService.salvarCliente(customerDTO))
                .build();

        log.info("ClienteController.cadastrarCliente - End");

        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponseDTO);
    }
}
