package br.com.erpsystem.mscliente.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_01_CLIENTE")
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
    private Date birthdate;
    @Column(name = "cpf", unique = true)
    private String cpf;


    @Column(name = "rg", nullable = false)
    private String rg;
    @Column(name = "register_date")
    private Date registerDate;
    @Column(name = "phone")
    private String phone;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private List<Address> addresses;


}
