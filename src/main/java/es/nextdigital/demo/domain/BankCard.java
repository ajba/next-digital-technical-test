package es.nextdigital.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "bank_card")
@Data
public class BankCard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Long number;

    // Encrypted field
    private String pin;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    private Boolean isActive;

    public enum CardType {
        DEBIT,
        CREDIT
    }

    @OneToOne
    private BankAccount bankAccount;
}
