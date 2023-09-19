package es.nextdigital.demo.repository;

import es.nextdigital.demo.domain.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankCardRepository extends JpaRepository<Long, BankCard> {
}
