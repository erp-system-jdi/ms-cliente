package br.com.erpsystem.mscustomer.services;

import br.com.erpsystem.mscustomer.dto.CustomerDTO;
import br.com.erpsystem.mscustomer.dto.http.request.RegisterCostumerRequestDTO;
import br.com.erpsystem.mscustomer.entity.Customer;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Spy
    private CustomerMapper customerMapper;

    private static final Customer CUSTOMER = Customer.builder()
            .registerDate(Timestamp.valueOf(LocalDateTime.now())).build();
    private static final CustomerDTO CUSTOMER_DTO = CustomerDTO.builder().build();
    private static final RegisterCostumerRequestDTO registerCustomerDTO = RegisterCostumerRequestDTO.builder()
            .customerDTO(CUSTOMER_DTO)
            .build();



    @Test
    void saveCustomer() {

        //when
        when(customerRepository.save(any())).thenReturn(CUSTOMER);
        //ação
        customerService.saveCustomer(registerCustomerDTO);
        //verify
        assertEquals(CustomerDTO.builder().build(), CUSTOMER_DTO);
    }

    @Test
    void findCustomerByCpf() {
    }
}