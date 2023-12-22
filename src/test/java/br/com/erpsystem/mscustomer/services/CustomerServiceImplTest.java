package br.com.erpsystem.mscustomer.services;

import br.com.erpsystem.mscustomer.dto.CustomerDTO;
import br.com.erpsystem.mscustomer.dto.http.request.CustomerUpdateRequestDTO;
import br.com.erpsystem.mscustomer.dto.http.request.RegisterCostumerRequestDTO;
import br.com.erpsystem.mscustomer.dto.http.response.CustomerUpdateResponseDTO;
import br.com.erpsystem.mscustomer.dto.http.response.RegisterCostumerResponseDTO;
import br.com.erpsystem.mscustomer.entity.Customer;
import br.com.erpsystem.mscustomer.exceptions.CustomerNotFoundException;
import br.com.erpsystem.mscustomer.exceptions.DuplicatedCpfException;
import br.com.erpsystem.mscustomer.mapper.*;
import br.com.erpsystem.mscustomer.repository.CustomerRepository;
import br.com.erpsystem.mscustomer.utils.JsonUtils;
import jakarta.annotation.security.RunAs;
import org.junit.jupiter.api.Assertions;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//@ContextConfiguration(classes = {
//        CustomerMapperImpl.class, AddressMapperImpl.class, DateMapper.class
//})
class CustomerServiceImplTest {

    @InjectMocks
    CustomerServiceImpl service;
    @Mock
    CustomerRepository repository;
    @Mock
    CustomerMapper mapper = new CustomerMapperImpl();

//    @Spy
//    private CustomerMapper mapper = Mappers.getMapper(CustomerMapper.class);
//    @Spy
//    private AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);





    private static final String CUSTOMER_JSON_PATH = "payloads/Customer.json";
    private static final String CUSTOMER_DTO_JSON_PATH = "payloads/Customer_DTO.json";
    private static final String REGISTER_CUSTOMER_REQUEST = "payloads/RegisterCustomerRequestDTO.json";
    private static final String REGISTER_CUSTOMER_RESPONSE = "payloads/RegisterCustomerResponseDTO.json";
    private static final String CUSTOMER_UPDATE_REQUEST_NOME_EMAIL = "payloads/RequestUpdateNomeEmail.json";
    private static final String CUSTOMER_UPDATE_REQUEST_TELEFONE_EMAIL = "payloads/RequestUpdateTelefoneEmail.json";
    private static final String CUSTOMER_UPDATE_REQUEST_ENDERECO = "payloads/RequestUpdateEndereco.json";
    private static final String CUSTOMER_UPDATE_RESPONSE_NOME_EMAIL = "payloads/CustomerUpdateResponseNomeEmail.json";
    private static final String CUSTOMER_UPDATED_NOME_EMAIL = "payloads/CustomerUpdatedNomeEmail.json";
    private static final String CUSTOMER_UPDATED_DTO_NOME_EMAIL = "payloads/CustomerDTOUpdatedNomeEmail.json";
    private static final String CUSTOMER_UPDATED_RESPONSE_TELEFONE_EMAIL = "payloads/CustomerUpdatedResponseTelefoneEmail.json";
    private static final String CUSTOMER_DTO_UPDATED_TELEFONE_EMAIL = "payloads/CustomerDTOUpdatedTelefoneEmail.json";
    private static final String CUSTOMER_UPDATED_TELEFONE_EMAIL = "payloads/CustomerUpdatedTelefoneEmail.json";
    private static final String CUSTOMER_UPDATED_ENDERECO = "payloads/CustomerUpdatedEndereco.json";
    private static final String CUSTOMER_UPDATED_RESPONSE_ENDERECO = "payloads/CustomerUpdatedResponseEndereco.json";
    private static final String CUSTOMER_DTO_UPDATED_ENDERECO = "payloads/CustomerDTOUpdatedEndereco.json";
    @Test
    void saveCustomer() throws IOException {

        Customer CUSTOMER = JsonUtils.getObjectFromFile(CUSTOMER_JSON_PATH, Customer.class);
        CustomerDTO CUSTOMER_DTO = JsonUtils.getObjectFromFile(CUSTOMER_DTO_JSON_PATH, CustomerDTO.class);
        RegisterCostumerRequestDTO REGISTER_REQUEST = JsonUtils.getObjectFromFile(REGISTER_CUSTOMER_REQUEST, RegisterCostumerRequestDTO.class);
        RegisterCostumerResponseDTO REGISTER_RESPONSE = JsonUtils.getObjectFromFile(REGISTER_CUSTOMER_RESPONSE, RegisterCostumerResponseDTO.class);

        when(mapper.customerToCustomerDTO(any())).thenReturn(CUSTOMER_DTO);
        when(mapper.customerDtoToCustomer(any())).thenReturn(CUSTOMER);

        when(repository.save(any())).thenReturn(CUSTOMER);

        RegisterCostumerResponseDTO responseDTO = service.saveCustomer(REGISTER_REQUEST);

        assertEquals(REGISTER_RESPONSE,responseDTO);
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

        RegisterCostumerResponseDTO responseDTO = service.findCustomerByCpf(CUSTOMER.getCpf());

        assertEquals(REGISTER_RESPONSE, responseDTO);

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
        CustomerDTO CUSTOMER_DTO = JsonUtils.getObjectFromFile(CUSTOMER_UPDATED_DTO_NOME_EMAIL, CustomerDTO.class);
        CustomerUpdateRequestDTO updateRequestNomeEmail = JsonUtils.getObjectFromFile(CUSTOMER_UPDATE_REQUEST_NOME_EMAIL, CustomerUpdateRequestDTO.class);
        CustomerUpdateResponseDTO updateResponseDTO = JsonUtils.getObjectFromFile(CUSTOMER_UPDATE_RESPONSE_NOME_EMAIL, CustomerUpdateResponseDTO.class);
        Customer customerUpdated = JsonUtils.getObjectFromFile(CUSTOMER_UPDATED_NOME_EMAIL, Customer.class);

        when(repository.getReferenceById(any())).thenReturn(CUSTOMER);
        when(mapper.updateCustomerFromDto(any(), any())).thenReturn(customerUpdated);
        when(mapper.customerToCustomerDTO(any())).thenReturn(CUSTOMER_DTO);
        when(repository.save(any())).thenReturn(customerUpdated);

        CustomerUpdateResponseDTO responseDTO = service.updateCustomer(customerId, updateRequestNomeEmail);

        assertEquals(updateResponseDTO, responseDTO);
    }
    @Test
    void updateCustomerTelefoneEmail() throws IOException {

        UUID customerId = UUID.fromString("ed4bec3e-dc03-4528-b506-fe9c5eaa5ea1");
        Customer CUSTOMER = JsonUtils.getObjectFromFile(CUSTOMER_JSON_PATH, Customer.class);
        CustomerDTO CUSTOMER_DTO = JsonUtils.getObjectFromFile(CUSTOMER_DTO_UPDATED_TELEFONE_EMAIL, CustomerDTO.class);
        Customer customerUpdated = JsonUtils.getObjectFromFile(CUSTOMER_UPDATED_TELEFONE_EMAIL, Customer.class);
        CustomerUpdateRequestDTO requestTelefoneEmail = JsonUtils.getObjectFromFile(CUSTOMER_UPDATE_REQUEST_TELEFONE_EMAIL, CustomerUpdateRequestDTO.class);
        CustomerUpdateResponseDTO updateResponseDTO = JsonUtils.getObjectFromFile(CUSTOMER_UPDATED_RESPONSE_TELEFONE_EMAIL, CustomerUpdateResponseDTO.class);

        when(repository.getReferenceById(any())).thenReturn(CUSTOMER);
        when(mapper.updateCustomerFromDto(any(), any())).thenReturn(customerUpdated);
        when(mapper.customerToCustomerDTO(any())).thenReturn(CUSTOMER_DTO);
        when(repository.save(any())).thenReturn(customerUpdated);

        CustomerUpdateResponseDTO responseDTO = service.updateCustomer(customerId,requestTelefoneEmail);

        Assertions.assertEquals(updateResponseDTO, responseDTO);
    }

    @Test
    void updateCustomerEndereco() throws IOException {

        UUID customerId = UUID.fromString("ed4bec3e-dc03-4528-b506-fe9c5eaa5ea1");
        Customer CUSTOMER = JsonUtils.getObjectFromFile(CUSTOMER_JSON_PATH, Customer.class);
        CustomerDTO CUSTOMER_DTO = JsonUtils.getObjectFromFile(CUSTOMER_DTO_UPDATED_ENDERECO, CustomerDTO.class);
        Customer customerUpdated = JsonUtils.getObjectFromFile(CUSTOMER_UPDATED_ENDERECO, Customer.class);
        CustomerUpdateRequestDTO requestEndereco = JsonUtils.getObjectFromFile(CUSTOMER_UPDATE_REQUEST_ENDERECO, CustomerUpdateRequestDTO.class);
        CustomerUpdateResponseDTO updateResponseDTO = JsonUtils.getObjectFromFile(CUSTOMER_UPDATED_RESPONSE_ENDERECO, CustomerUpdateResponseDTO.class);

        when(repository.getReferenceById(any())).thenReturn(CUSTOMER);
        when(mapper.updateCustomerFromDto(any(), any())).thenReturn(customerUpdated);
        when(mapper.customerToCustomerDTO(any())).thenReturn(CUSTOMER_DTO);
        when(repository.save(any())).thenReturn(customerUpdated);

        CustomerUpdateResponseDTO responseDTO = service.updateCustomer(customerId,requestEndereco);

        Assertions.assertEquals(updateResponseDTO, responseDTO);
    }
    @Test
    void updatedCustomerNotFound() throws IOException {

        UUID customerId = UUID.fromString("ed4bec3e-dc03-4528-b506-fe9c5eaa5ea1");
        CustomerUpdateRequestDTO request = JsonUtils.getObjectFromFile(CUSTOMER_UPDATE_REQUEST_ENDERECO, CustomerUpdateRequestDTO.class);

        when(repository.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(CustomerNotFoundException.class, () ->{
            service.updateCustomer(customerId, request);
        });
    }



}
