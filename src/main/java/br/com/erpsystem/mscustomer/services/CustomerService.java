package br.com.erpsystem.mscustomer.services;


import br.com.erpsystem.mscustomer.dto.http.request.RegisterCostumerRequestDTO;
import br.com.erpsystem.mscustomer.dto.http.response.RegisterCostumerResponseDTO;

public interface CustomerService {

    RegisterCostumerResponseDTO saveCustomer(RegisterCostumerRequestDTO registerCostumerRequestDTO);

    RegisterCostumerResponseDTO findCustomerByCpf(String cpf);
}
