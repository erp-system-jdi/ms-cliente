package br.com.erpsystem.mscustomer.services;

import br.com.erpsystem.mscustomer.dto.CustomerDTO;
import br.com.erpsystem.mscustomer.dto.http.request.CustomerUpdateRequestDTO;
import br.com.erpsystem.mscustomer.dto.http.request.RegisterCostumerRequestDTO;
import br.com.erpsystem.mscustomer.dto.http.response.RegisterCostumerResponseDTO;
import br.com.erpsystem.mscustomer.entity.Customer;
import br.com.erpsystem.mscustomer.exceptions.CustomerNotFoundException;
import br.com.erpsystem.mscustomer.exceptions.DuplicatedCpfException;
import br.com.erpsystem.mscustomer.mapper.CustomerMapper;
import br.com.erpsystem.mscustomer.mapper.CustomerMapperImpl;
import br.com.erpsystem.mscustomer.mapper.DateMapper;
import br.com.erpsystem.mscustomer.repository.CustomerRepository;
import br.com.erpsystem.mscustomer.utils.JsonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    CustomerServiceImpl service;
    @Mock
    CustomerMapper mapper = new CustomerMapperImpl();
    @Mock
    CustomerRepository repository;

    private static final String CUSTOMER_JSON_PATH = "payloads/Customer.json";
    private static final String CUSTOMER_DTO_JSON_PATH = "payloads/Customer_DTO.json";
    private static final String REGISTER_CUSTOMER_REQUEST = "payloads/RegisterCustomerRequestDTO.json";
    private static final String REGISTER_CUSTOMER_RESPONSE = "payloads/RegisterCustomerResponseDTO.json";
    private static final String CUSTOMER_UPDATE_REQUEST_NOME_EMAIL = "payloads/RequestUpdateNomeEmail.json";
    private static final String CUSTOMER_UPDATE_REQUEST_TELEFONE_EMAIL = "payloads/RequestUpdateTelefoneEmail.json";
    private static final String CUSTOMER_UPDATE_REQUEST_ENDERECO = "payloads/RequestUpdateEndereco.json";



    @Test
    void saveCustomer() throws IOException {

        Customer CUSTOMER = JsonUtils.getObjectFromFile(CUSTOMER_JSON_PATH, Customer.class);
        CustomerDTO CUSTOMER_DTO = JsonUtils.getObjectFromFile(CUSTOMER_DTO_JSON_PATH, CustomerDTO.class);
        RegisterCostumerRequestDTO REGISTER_REQUEST = JsonUtils.getObjectFromFile(REGISTER_CUSTOMER_REQUEST, RegisterCostumerRequestDTO.class);
        RegisterCostumerResponseDTO REGISTER_RESPONSE = JsonUtils.getObjectFromFile(REGISTER_CUSTOMER_RESPONSE, RegisterCostumerResponseDTO.class);

        when(mapper.customerToCustomerDTO(any())).thenReturn(CUSTOMER_DTO);
        when(mapper.customerDtoToCustomer(any())).thenReturn(CUSTOMER);

        when(repository.save(any())).thenReturn(CUSTOMER);

        service.saveCustomer(REGISTER_REQUEST);


        assertEquals(REGISTER_RESPONSE.getCustomerDTO().getCpf(), CUSTOMER_DTO.getCpf() );
    }

    @Test
    void saveCustomerCpfDuplicated() throws IOException {

        Customer CUSTOMER = JsonUtils.getObjectFromFile(CUSTOMER_JSON_PATH, Customer.class);
        RegisterCostumerRequestDTO REGISTER_REQUEST = JsonUtils.getObjectFromFile(REGISTER_CUSTOMER_REQUEST, RegisterCostumerRequestDTO.class);

        when(repository.findCustomerByCpf(any())).thenReturn(Optional.of(CUSTOMER));

        assertThrows(DuplicatedCpfException.class, () -> {
            service.saveCustomer(REGISTER_REQUEST);
        });
    }

    @Test
    void findCustomer() throws IOException {

        Customer CUSTOMER = JsonUtils.getObjectFromFile(CUSTOMER_JSON_PATH, Customer.class);
        RegisterCostumerResponseDTO REGISTER_RESPONSE = JsonUtils.getObjectFromFile(REGISTER_CUSTOMER_RESPONSE, RegisterCostumerResponseDTO.class);
        CustomerDTO CUSTOMER_DTO = JsonUtils.getObjectFromFile(CUSTOMER_DTO_JSON_PATH, CustomerDTO.class);

        when(mapper.customerToCustomerDTO(any())).thenReturn(CUSTOMER_DTO);

        when(repository.findCustomerByCpf(any())).thenReturn(Optional.of(CUSTOMER));

        service.findCustomerByCpf(CUSTOMER.getCpf());

        assertEquals(REGISTER_RESPONSE.getCustomerDTO(), CUSTOMER_DTO);

    }

    @Test
    void findCustomerNotFound() throws IOException {

        CustomerDTO CUSTOMER_DTO = JsonUtils.getObjectFromFile(CUSTOMER_DTO_JSON_PATH, CustomerDTO.class);


        when(repository.findCustomerByCpf(any())).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> {
            service.findCustomerByCpf(CUSTOMER_DTO.getCpf());
        });
    }
    @Test
    void updateCustomerNomeAndEmail() throws IOException {

        UUID customerId = UUID.fromString("ed4bec3e-dc03-4528-b506-fe9c5eaa5ea1");
        Customer CUSTOMER = JsonUtils.getObjectFromFile(CUSTOMER_JSON_PATH, Customer.class);
        CustomerDTO CUSTOMER_DTO = JsonUtils.getObjectFromFile(CUSTOMER_DTO_JSON_PATH, CustomerDTO.class);
        CustomerUpdateRequestDTO updateRequestNomeEmail = JsonUtils.getObjectFromFile(CUSTOMER_UPDATE_REQUEST_NOME_EMAIL, CustomerUpdateRequestDTO.class);

        when(repository.getReferenceById(any())).thenReturn(CUSTOMER);
        when(mapper.customerToCustomerDTO(any())).thenReturn(CUSTOMER_DTO);
        when(repository.save(any())).thenReturn(CUSTOMER);

        service.updateCustomer(customerId, updateRequestNomeEmail);
    }

//    void updateCustomerTelefoneEmail



}
