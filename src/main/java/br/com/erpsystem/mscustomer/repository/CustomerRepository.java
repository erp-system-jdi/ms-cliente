package br.com.erpsystem.mscustomer.repository;

import br.com.erpsystem.mscustomer.entity.Address;
import br.com.erpsystem.mscustomer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findCustomerByCpf(String cpf);

//    @Modifying
//    @Query("UPDATE TB_01_CUSTOMER c SET c.lastName = :lastName, c.fullName = :fullName," +
//            "c.email = :email, c.phone = :phone WHERE c.id = :customerId")
//    void updateCustomer(@Param("customerId") UUID customerId, @Param("lastName") String lastName, @Param("fullName") String fullName, @Param("email") String email, @Param("phone") String phone);
//


}
