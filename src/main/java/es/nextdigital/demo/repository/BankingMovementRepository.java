package es.nextdigital.demo.repository;

import es.nextdigital.demo.domain.BankingMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BankingMovementRepository extends JpaRepository<Long, BankingMovement> {

    @Query("select bm from BankingMovement bm where bm.")
    BankingMovement findByUserIdAndIbanAndMoveType(Long userId, String iban, String move);
}
