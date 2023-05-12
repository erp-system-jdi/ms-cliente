package br.com.erpsystem.mscustomer.repository;

import br.com.erpsystem.mscustomer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findCustomerByCpf(String cpf);
}
