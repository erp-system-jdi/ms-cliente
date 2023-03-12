package br.com.erpsystem.mscliente.mapper;

import br.com.erpsystem.mscliente.dto.ClienteDTO;
import br.com.erpsystem.mscliente.entity.Cliente;
import org.mapstruct.Mapper;

@Mapper
public interface ClienteMapper {

    Cliente clienteDtoToCliente(ClienteDTO clienteDTO);
    ClienteDTO clienteToClienteDTO(Cliente cliente);
}
