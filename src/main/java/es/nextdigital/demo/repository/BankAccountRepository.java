package es.nextdigital.demo.repository;

import es.nextdigital.demo.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<Long, BankAccount> {

    @Query("select ba from BankAccount ba where ba.accountOwner.id = ?1 and ba.iban = ?2 and ba.bankingMovements IN (?3)")
    BankAccount findByUserIdAndIbanAndMoveType(Long userId, String iban, String move);
}
