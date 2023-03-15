package br.com.erpsystem.mscliente.repository;

import br.com.erpsystem.mscliente.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
