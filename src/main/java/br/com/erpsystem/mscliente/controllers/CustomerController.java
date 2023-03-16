package br.com.erpsystem.mscliente.controllers;

import br.com.erpsystem.mscliente.dto.CustomerDTO;
import br.com.erpsystem.mscliente.dto.http.request.RegisterCostumerRequestDTO;
import br.com.erpsystem.mscliente.dto.http.response.CustomerResponseDTO;
import br.com.erpsystem.mscliente.dto.http.response.RegisterCostumerResponseDTO;
import br.com.erpsystem.mscliente.services.CustomerService;
import jakarta.validation.Valid;
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
    public ResponseEntity<RegisterCostumerResponseDTO> searchCustomerByCpf(@PathVariable("cpf") String cpf){
        log.info("CustomerController.searchCustomerByCpf - Start - cpf: {}", cpf);
        RegisterCostumerResponseDTO registerCostumerResponseDTO = customerService.findCustomerByCpf(cpf);
        log.info("ClienteController.buscarClientePorCpf - End");
        return ResponseEntity.ok(registerCostumerResponseDTO);
    }


    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterCostumerResponseDTO> registerCustomer(@Valid @RequestBody RegisterCostumerRequestDTO registerCostumerRequestDTO){
        log.info("CustomerController.cadastrarCliente - Start - registerCustomerRequestDTO: {}", registerCostumerRequestDTO);

        RegisterCostumerResponseDTO customerResponseDTO = RegisterCostumerResponseDTO.builder()
                .costumerDTO(customerService.saveCustomer(registerCostumerRequestDTO).getCostumerDTO()).build();

        log.info("ClienteController.cadastrarCliente - End");

        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponseDTO);
    }

}
