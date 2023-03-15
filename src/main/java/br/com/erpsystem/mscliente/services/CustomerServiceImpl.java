package br.com.erpsystem.mscliente.services;

import br.com.erpsystem.mscliente.dto.CustomerDTO;
import br.com.erpsystem.mscliente.entity.Customer;
import br.com.erpsystem.mscliente.exceptions.BusinessExceptions;
import br.com.erpsystem.mscliente.exceptions.CustomerNotFoundException;
import br.com.erpsystem.mscliente.mapper.CustomerMapper;
import br.com.erpsystem.mscliente.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    @Override
    public CustomerDTO salvarCliente(CustomerDTO customerDTO) {
        log.info("ClienteServiceImpl.salvarCliente - Start - clienteDTO: {}", customerDTO);
        Customer savedCustomer = customerRepository.save(mapper.clienteDtoToCliente(customerDTO));
        log.info("ClienteServiceImpl.salvarCliente - End - cliente: {}", savedCustomer);
        return mapper.clienteToClienteDTO(savedCustomer);
    }

    @Override
    public CustomerDTO findCustomerByCpf(String cpf) {

//        log.info("CustomerServiceImpl.findCustomerByCpf - Start - Cpf Cliente: {}", cpf);
//
//        CustomerDTO customerDTO = mapper.clienteToClienteDTO(customerRepository.findCustomerByCpf(cpf));
//
//        if(customerDTO == null){
//            log.error("CustomerServiceImpl.findCustomerByCpf - Error - Client Not Found");
//            throw new CustomerNotFoundException("Client Not Found");
//        }
//
//        log.info("CustomerServiceImpl.findCustomerByCpf - End");
//        return customerDTO;
//
//    }

        CustomerDTO customerDTO = mapper.clienteToClienteDTO((customerRepository.findCustomerByCpf(cpf)
                .orElseThrow(() -> {
                        log.error("CustomerServiceImpl.findCustomerByCpf - Error - Client Not Found");
                        throw new CustomerNotFoundException(BusinessExceptions.CUSTOMER_NOT_FOUND);
                })));

        log.info("CustomerServiceImpl.findCustomerByCpf - End");
        return customerDTO;

        }

}
