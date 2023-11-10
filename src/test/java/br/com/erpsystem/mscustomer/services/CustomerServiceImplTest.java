package br.com.erpsystem.mscustomer.services;

import br.com.erpsystem.mscustomer.dto.CustomerDTO;
import br.com.erpsystem.mscustomer.dto.http.request.RegisterCostumerRequestDTO;
import br.com.erpsystem.mscustomer.dto.http.response.RegisterCostumerResponseDTO;
import br.com.erpsystem.mscustomer.entity.Customer;
import br.com.erpsystem.mscustomer.exceptions.CustomerNotFoundException;
import br.com.erpsystem.mscustomer.exceptions.DuplicatedCpfException;
import br.com.erpsystem.mscustomer.mapper.CustomerMapper;
import br.com.erpsystem.mscustomer.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    CustomerServiceImpl service;
    @Spy
    CustomerMapper mapper;

    @Mock
    CustomerRepository repository;

    Customer CUSTOMER = Customer.builder()
            .cpf("42946578801")
            .build();

    CustomerDTO Customer_DTO = CustomerDTO.builder()
            .cpf("42946578801")
            .build();

    RegisterCostumerRequestDTO registerCostumer = RegisterCostumerRequestDTO.builder()
            .customerDTO(Customer_DTO)
            .build();

    RegisterCostumerResponseDTO RESPONSE = RegisterCostumerResponseDTO.builder()
            .customerDTO(Customer_DTO)
            .build();
    @Test
    void saveCustomer(){

        when(repository.save(any())).thenReturn(CUSTOMER);

        service.saveCustomer(registerCostumer);


        assertEquals(RESPONSE.getCustomerDTO().getCpf(), Customer_DTO.getCpf() );
    }

    @Test
    void saveCustomerCpfDuplicated(){

        when(repository.findCustomerByCpf(any())).thenReturn(Optional.of(CUSTOMER));

        assertThrows(DuplicatedCpfException.class, () -> {
            service.saveCustomer(registerCostumer);
        });
    }

    @Test
    void findCustomer(){

        when(repository.findCustomerByCpf(any())).thenReturn(Optional.of(CUSTOMER));

        service.findCustomerByCpf(CUSTOMER.getCpf());

        assertEquals(RESPONSE.getCustomerDTO(), Customer_DTO);

    }

    @Test
    void findCustomerNotFound(){

        when(repository.findCustomerByCpf(any())).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> {
            service.findCustomerByCpf(Customer_DTO.getCpf());
        });
    }

}
