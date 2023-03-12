package br.com.erpsystem.mscliente.services;


import br.com.erpsystem.mscliente.dto.ClienteDTO;

public interface ClienteService {

    ClienteDTO salvarCliente(ClienteDTO clienteDTO);

    ClienteDTO buscarClientePorCpf(String cpf);
}
