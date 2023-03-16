package br.com.erpsystem.mscliente.services;

import br.com.erpsystem.mscliente.dto.http.request.RegisterCostumerRequestDTO;
import br.com.erpsystem.mscliente.dto.http.response.RegisterCostumerResponseDTO;
import br.com.erpsystem.mscliente.entity.Customer;
import br.com.erpsystem.mscliente.exceptions.DuplicatedCpfException;
import br.com.erpsystem.mscliente.exceptions.InvalidDataException;
import br.com.erpsystem.mscliente.mapper.AddressMapperImpl;
import br.com.erpsystem.mscliente.mapper.CustomerMapper;
import br.com.erpsystem.mscliente.mapper.CustomerMapperImpl;
import br.com.erpsystem.mscliente.mapper.DateMapper;
import br.com.erpsystem.mscliente.repository.CustomerRepository;
import br.com.erpsystem.mscliente.utils.JsonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    private static final String REGISTER_CUSTOMER_RESPONSE_SUCESS = "json/response/resgister_customer_response_sucess.json";
    private static final String  REGISTER_CUSTOMER_REQUEST_SUCESS = "json/request/register_customer_request_sucess.json";
    private static final String  REGISTER_CUSTOMER_REQUEST_CPF_FAILED = "json/request/register_customer_request_cpf_failed.json";
    private static final Customer CUSTOMER = Customer.builder().build();

    @InjectMocks
    private CustomerServiceImpl customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;

    @Test
    void saveCustomer() throws IOException {

        String registerCustomerResponse = JsonUtils.readJsonFile(REGISTER_CUSTOMER_RESPONSE_SUCESS);
        RegisterCostumerResponseDTO expectedResponse = JsonUtils.getObjectFromJson(registerCustomerResponse, RegisterCostumerResponseDTO.class);

        String registerCustomerRequest = JsonUtils.readJsonFile(REGISTER_CUSTOMER_REQUEST_SUCESS);
        RegisterCostumerRequestDTO request = JsonUtils.getObjectFromJson(registerCustomerRequest, RegisterCostumerRequestDTO.class);

        when(customerMapper.customerToCustomerDTO(any())).thenReturn(expectedResponse.getCostumerDTO());
        when(customerRepository.save(any())).thenReturn(CUSTOMER);

        RegisterCostumerResponseDTO response = customerService.saveCustomer(request);

        assertEquals(expectedResponse, response);

    }

    @Test
    void saveCustomerCpfDuplicated() throws IOException {

        String registerCustomerRequestCpfFailed = JsonUtils.readJsonFile(REGISTER_CUSTOMER_REQUEST_CPF_FAILED);
        RegisterCostumerRequestDTO request = JsonUtils.getObjectFromJson(registerCustomerRequestCpfFailed, RegisterCostumerRequestDTO.class);

        when(customerRepository.findCustomerByCpf(any()).isPresent()).thenReturn(true);

        assertThrows(DuplicatedCpfException.class , () -> {
            customerService.saveCustomer(request);
        });
    }

    @Test
    void findCustomerByCpf() {
    }

}