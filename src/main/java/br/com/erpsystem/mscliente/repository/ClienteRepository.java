package br.com.erpsystem.mscliente.repository;

import br.com.erpsystem.mscliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    Cliente findClienteByCpf(String cpf);
}
