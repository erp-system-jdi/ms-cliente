package br.com.erpsystem.mscustomer.services;


import br.com.erpsystem.mscustomer.dto.http.request.CustomerUpdateRequestDTO;
import br.com.erpsystem.mscustomer.dto.http.request.RegisterCostumerRequestDTO;
import br.com.erpsystem.mscustomer.dto.http.response.CustomerUpdateResponseDTO;
import br.com.erpsystem.mscustomer.dto.http.response.RegisterCostumerResponseDTO;

import java.util.UUID;

public interface CustomerService {

    RegisterCostumerResponseDTO saveCustomer(RegisterCostumerRequestDTO registerCostumerRequestDTO);

    RegisterCostumerResponseDTO findCustomerByCpf(String cpf);

    CustomerUpdateResponseDTO updateCustomer(UUID customerId, CustomerUpdateRequestDTO customerUpdateRequestDTO);

}
