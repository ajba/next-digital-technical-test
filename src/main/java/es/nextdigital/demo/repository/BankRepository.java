package es.nextdigital.demo.repository;

import es.nextdigital.demo.domain.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Long, Bank> {
}
