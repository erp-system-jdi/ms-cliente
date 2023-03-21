package br.com.erpsystem.mscliente.services;


import br.com.erpsystem.mscliente.dto.CustomerDTO;
import br.com.erpsystem.mscliente.dto.http.request.RegisterCostumerRequestDTO;
import br.com.erpsystem.mscliente.dto.http.response.RegisterCostumerResponseDTO;

public interface CustomerService {

    RegisterCostumerResponseDTO saveCustomer(RegisterCostumerRequestDTO registerCostumerRequestDTO);

    RegisterCostumerResponseDTO findCustomerByCpf(String cpf);
}
