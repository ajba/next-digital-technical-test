package es.nextdigital.demo.domain;

import es.nextdigital.demo.validators.Nif;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "bank_user")
@Data
public class BankUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @Nif
    private String nif;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "accountOwner")
    private List<BankAccount> bankAccounts;
}
