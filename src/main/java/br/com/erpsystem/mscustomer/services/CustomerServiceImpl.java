package br.com.erpsystem.mscustomer.services;

import br.com.erpsystem.mscustomer.dto.CustomerDTO;
import br.com.erpsystem.mscustomer.dto.http.request.CustomerUpdateRequestDTO;
import br.com.erpsystem.mscustomer.dto.http.request.RegisterCostumerRequestDTO;
import br.com.erpsystem.mscustomer.dto.http.response.CustomerUpdateResponseDTO;
import br.com.erpsystem.mscustomer.dto.http.response.RegisterCostumerResponseDTO;
import br.com.erpsystem.mscustomer.entity.Customer;
import br.com.erpsystem.mscustomer.enums.ErrorCodes;
import br.com.erpsystem.mscustomer.exceptions.CustomerNotFoundException;
import br.com.erpsystem.mscustomer.exceptions.DuplicatedCpfException;
import br.com.erpsystem.mscustomer.exceptions.ExceptionResponse;
import br.com.erpsystem.mscustomer.mapper.AddressMapper;
import br.com.erpsystem.mscustomer.mapper.CustomerMapper;
import br.com.erpsystem.mscustomer.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static br.com.erpsystem.mscustomer.constants.BusinessErrorConstants.CUSTOMER_NOT_FOUND;
import static br.com.erpsystem.mscustomer.constants.BusinessErrorConstants.DUPLICATED_CPF;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;
    private final AddressMapper addressMapper;



    @Override
    public RegisterCostumerResponseDTO saveCustomer(RegisterCostumerRequestDTO registerCostumerRequestDTO) {
        if(verifyCustomerExists(registerCostumerRequestDTO.getCustomerDTO().getCpf())){
            throw new DuplicatedCpfException(new ExceptionResponse(ErrorCodes.DUPLICATED_CPF, DUPLICATED_CPF));
        } else {
            log.info("CustomerServiceImpl.saveCustomer - Start - registerCostumerRequestDTO: {}", registerCostumerRequestDTO);
            registerCostumerRequestDTO.getCustomerDTO().setRegisterDate(LocalDateTime.now());
            Customer savedCustomer = customerRepository.save(mapper.customerDtoToCustomer(registerCostumerRequestDTO.getCustomerDTO()));
            log.info("CustomerServiceImpl.saveCustomer - End - customer: {}", savedCustomer);

            CustomerDTO customerDTO = mapper.customerToCustomerDTO(savedCustomer);
            customerDTO.setFullName(savedCustomer.getFirstName().concat(" ").concat(savedCustomer.getLastName()));

            return RegisterCostumerResponseDTO.builder()
                    .customerDTO(customerDTO).build();

        }
    }

    @Override
    @Transactional
    public RegisterCostumerResponseDTO findCustomerByCpf(String cpf) {
        log.info("CustomerServiceImpl.findCustomerByCpf - Start - Cpf: {}", cpf);

        CustomerDTO customerDTO = mapper.customerToCustomerDTO((customerRepository.findCustomerByCpf(cpf)
                .orElseThrow(() -> {
                    log.error("CustomerServiceImpl.findCustomerByCpf - Error - Customer Not Found");
                    throw new CustomerNotFoundException(new ExceptionResponse(ErrorCodes.INVALID_REQUEST, CUSTOMER_NOT_FOUND));
                })));

        customerDTO.setFullName(customerDTO.getFirstName().concat(" ").concat(customerDTO.getLastName()));

        log.info("CustomerServiceImpl.findCustomerByCpf - End");
        return registerCostumerResponseBuilder(customerDTO);

    }

    @Override
    @Transactional
    public CustomerUpdateResponseDTO updateCustomer(UUID customerId, CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        log.info("CustomerServiceImpl.updateCustomer - Start - Customer Id: {} and CustomerUpdateRequest: {}", customerId, customerUpdateRequestDTO);

        Customer customer = Optional.of(customerRepository.getReferenceById(customerId)).
                orElseThrow( () -> new CustomerNotFoundException(new ExceptionResponse(ErrorCodes.CUSTOMER_NOT_FOUND, CUSTOMER_NOT_FOUND)));

        log.info("CustomerServiceImpl.updateCustomer - Entidade do banco: {}", customer);

        mapper.updateCustomerFromDto(customerUpdateRequestDTO, customer);
        customerRepository.save(customer);

        log.info("CustomerServiceImpl.updateCustomer - Customer Updated: {}", customer);

        CustomerDTO customerDTO = mapper.customerToCustomerDTO(customer);
        customerDTO.setFullName(customer.getFirstName().concat(" ").concat(customer.getLastName()));

//        CustomerUpdateResponseDTO responseDTO = CustomerUpdateResponseDTO.builder()
//                .customerDTO(customerDTO)
//                .build();

        return CustomerUpdateResponseDTO.builder().customerDTO(customerDTO).build();
    }

    private boolean verifyCustomerExists(String cpf){
        return customerRepository.findCustomerByCpf(cpf).isPresent();
    }

    private RegisterCostumerResponseDTO registerCostumerResponseBuilder(CustomerDTO customerDTO){
        return RegisterCostumerResponseDTO.builder()
                .customerDTO(customerDTO)
                .build();
    }

}
