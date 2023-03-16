package br.com.erpsystem.mscliente.services;

import br.com.erpsystem.mscliente.dto.CustomerDTO;
import br.com.erpsystem.mscliente.dto.http.request.RegisterCostumerRequestDTO;
import br.com.erpsystem.mscliente.dto.http.response.RegisterCostumerResponseDTO;
import br.com.erpsystem.mscliente.entity.Customer;
import br.com.erpsystem.mscliente.exceptions.BusinessExceptions;
import br.com.erpsystem.mscliente.exceptions.CustomerNotFoundException;
import br.com.erpsystem.mscliente.exceptions.DuplicatedCpfException;
import br.com.erpsystem.mscliente.mapper.CustomerMapper;
import br.com.erpsystem.mscliente.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    @Override
    public RegisterCostumerResponseDTO saveCustomer(RegisterCostumerRequestDTO registerCostumerRequestDTO) {
        if(verifyCustomerExists(registerCostumerRequestDTO.getCustomerDTO().getCpf())){
            throw new DuplicatedCpfException("This CPF already exists in the database!");
        } else {
            log.info("CustomerServiceImpl.saveCustomer - Start - registerCostumerRequestDTO: {}", registerCostumerRequestDTO);
            registerCostumerRequestDTO.getCustomerDTO().setRegisterDate(LocalDateTime.now());
            Customer savedCustomer = customerRepository.save(mapper.customerDtoToCustomer(registerCostumerRequestDTO.getCustomerDTO()));
            log.info("CustomerServiceImpl.saveCustomer - End - customer: {}", savedCustomer);
            return RegisterCostumerResponseDTO.builder()
                    .costumerDTO(mapper.customerToCustomerDTO(savedCustomer)).build();

        }
    }

    @Override
    public RegisterCostumerResponseDTO findCustomerByCpf(String cpf) {

        CustomerDTO customerDTO = mapper.customerToCustomerDTO((customerRepository.findCustomerByCpf(cpf)
                .orElseThrow(() -> {
                    log.error("CustomerServiceImpl.findCustomerByCpf - Error - Customer Not Found");
                    throw new CustomerNotFoundException(BusinessExceptions.CUSTOMER_NOT_FOUND);
                })));

        log.info("CustomerServiceImpl.findCustomerByCpf - End");
        return registerCostumerResponseBuilder(customerDTO);

    }

    private boolean verifyCustomerExists(String cpf){

        Optional<Customer> customer = customerRepository.findCustomerByCpf(cpf);

        if(customer.isPresent()){
            return true;
        } else{
            return false;
        }
    }

    private RegisterCostumerResponseDTO registerCostumerResponseBuilder(CustomerDTO customerDTO){
        return RegisterCostumerResponseDTO.builder()
                .costumerDTO(customerDTO)
                .build();
    }

}
