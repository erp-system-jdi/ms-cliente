package br.com.erpsystem.mscliente.services;

import br.com.erpsystem.mscliente.dto.ClienteDTO;
import br.com.erpsystem.mscliente.entity.Cliente;
import br.com.erpsystem.mscliente.mapper.ClienteMapper;
import br.com.erpsystem.mscliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper mapper;

    @Override
    public ClienteDTO salvarCliente(ClienteDTO clienteDTO) {
        log.info("ClienteServiceImpl.salvarCliente - Start - clienteDTO: {}", clienteDTO);
        Cliente savedCliente = clienteRepository.save(mapper.clienteDtoToCliente(clienteDTO));
        log.info("ClienteServiceImpl.salvarCliente - End - cliente: {}", savedCliente);
        return mapper.clienteToClienteDTO(savedCliente);
    }

    @Override
    public ClienteDTO buscarClientePorCpf(String cpf) {
        log.info("ClienteServiceImpl.salvarCliente - Start - Cpf Cliente: {}", cpf);
        ClienteDTO clienteDTO = mapper.clienteToClienteDTO(clienteRepository.findClienteByCpf(cpf));
        log.info("ClienteServiceImpl.salvarCliente - End - cliente: {}", clienteDTO);
        return clienteDTO;
    }
}
