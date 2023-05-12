package br.com.erpsystem.mscustomer.repository;

import br.com.erpsystem.mscustomer.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
