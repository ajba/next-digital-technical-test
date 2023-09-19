package es.nextdigital.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "baking_movement")
@Data
public class BankingMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Moves move;

    public enum Moves {
        DEPOSIT,
        WITHDRAWAL,
        COMMISSIONS,
        TRANSFER_IN,
        TRANSFER_OUT
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "baking_movement_bank_account",
            joinColumns = { @JoinColumn(name = "bank_account_id") },
            inverseJoinColumns = { @JoinColumn(name = "baking_movement_id") })
    private Set<BankAccount> bankAccounts = new HashSet<>();
}
