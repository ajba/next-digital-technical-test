package es.nextdigital.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.nextdigital.demo.validators.Iban;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bank_account")
@Data
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="account_number")
    @NotNull
    private Long accountNumber;

    @Iban
    private String iban;

    private Long balance;

    @ManyToOne(fetch = FetchType.LAZY)
    private BankUser accountOwner;

    @OneToOne
    private Bank bank;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "bankAccounts")
    @JsonIgnore
    private Set<BankingMovement> bankingMovements = new HashSet<>();

}
