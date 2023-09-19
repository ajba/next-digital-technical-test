package es.nextdigital.demo.repository;

import es.nextdigital.demo.domain.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankUserRepository extends JpaRepository<Long, BankUser> {
}
