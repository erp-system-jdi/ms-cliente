package br.com.erpsystem.mscliente.services;


import br.com.erpsystem.mscliente.dto.CustomerDTO;

public interface CustomerService {

    CustomerDTO salvarCliente(CustomerDTO customerDTO);

    CustomerDTO findCustomerByCpf(String cpf);
}
