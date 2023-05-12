package br.com.erpsystem.mscustomer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_01_CUSTOMER")
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "birthdate")
    private Timestamp birthdate;
    @Column(name = "email")
    private String email;
    @Column(name = "cpf", unique = true)
    private String cpf;
    @Column(name = "rg", nullable = false)
    private String rg;
    @Column(name = "register_date")
    private Timestamp registerDate;
    @Column(name = "phone")
    private String phone;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private List<Address> addresses;


}
