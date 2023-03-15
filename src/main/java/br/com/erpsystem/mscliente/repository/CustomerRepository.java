package br.com.erpsystem.mscliente.repository;

import br.com.erpsystem.mscliente.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Customer findClienteByCpf(String cpf);
}
