package br.com.erpsystem.mscustomer.controllers;

import br.com.erpsystem.mscustomer.dto.http.request.RegisterCostumerRequestDTO;
import br.com.erpsystem.mscustomer.dto.http.response.RegisterCostumerResponseDTO;
import br.com.erpsystem.mscustomer.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterCostumerResponseDTO> searchCustomerByCpf(@PathVariable("cpf") String cpf){
        log.info("CustomerController.searchCustomerByCpf - Start - cpf: {}", cpf);
        RegisterCostumerResponseDTO registerCostumerResponseDTO = customerService.findCustomerByCpf(cpf);
        log.info("CustomerController.searchCustomerByCpf - End");
        return ResponseEntity.ok(registerCostumerResponseDTO);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterCostumerResponseDTO> registerCustomer(@Valid @RequestBody RegisterCostumerRequestDTO registerCostumerRequestDTO){
        log.info("CustomerController.registerCustomer - Start - registerCustomerRequestDTO: {}", registerCostumerRequestDTO);

        RegisterCostumerResponseDTO customerResponseDTO = RegisterCostumerResponseDTO.builder()
                .costumerDTO(customerService.saveCustomer(registerCostumerRequestDTO).getCostumerDTO()).build();

        log.info("CustomerController.registerCustomer - End");

        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponseDTO);
    }

}
