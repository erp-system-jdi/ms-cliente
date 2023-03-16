package br.com.erpsystem.mscliente.services;

import br.com.erpsystem.mscliente.dto.CustomerDTO;
import br.com.erpsystem.mscliente.dto.http.request.RegisterCostumerRequestDTO;
import br.com.erpsystem.mscliente.dto.http.response.RegisterCostumerResponseDTO;
import br.com.erpsystem.mscliente.entity.Customer;
import br.com.erpsystem.mscliente.mapper.CustomerMapper;
import br.com.erpsystem.mscliente.repository.CustomerRepository;
import br.com.erpsystem.mscliente.utils.JsonUtils;
import org.bouncycastle.util.Times;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    private static final String REGISTER_CUSTOMER_RESPONSE_SUCESS = "json/response/resgister_customer_response_sucess.json";
    private static final String  REGISTER_CUSTOMER_REQUEST_SUCESS = "json/request/register_customer_request_sucess.json";


    @InjectMocks
    private CustomerServiceImpl customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Spy
    private CustomerMapper customerMapper;

    @Test
    void saveCustomer() throws IOException {

        String registerCustomerResponse = JsonUtils.readJsonFile(REGISTER_CUSTOMER_RESPONSE_SUCESS);
        RegisterCostumerResponseDTO expectedResponse = JsonUtils.getObjectFromJson(registerCustomerResponse, RegisterCostumerResponseDTO.class);

        String registerCustomerRequest = JsonUtils.readJsonFile(REGISTER_CUSTOMER_REQUEST_SUCESS);
        RegisterCostumerRequestDTO request = JsonUtils.getObjectFromJson(registerCustomerRequest, RegisterCostumerRequestDTO.class);

        when(customerRepository.save(any())).thenReturn(customerMapper.customerDtoToCustomer(expectedResponse.getCostumerDTO()));

        //ação
        RegisterCostumerResponseDTO response = customerService.saveCustomer(request);

        //verify
        assertEquals(expectedResponse, response);

    }

    @Test
    void findCustomerByCpf() {
    }

}