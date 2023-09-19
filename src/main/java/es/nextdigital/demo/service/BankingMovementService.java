package es.nextdigital.demo.service;

import es.nextdigital.demo.repository.BankingMovementRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankingMovementService {

    private final BankingMovementRepository bankingMovementRepository;


    public BankingMovementService(BankingMovementRepository bankingMovementRepository) {
        this.bankingMovementRepository = bankingMovementRepository;
    }

    public void findByUserIdAndIbanAndMoveType(Long userId, String iban, String move) {
        this.bankingMovementRepository.findByUserIdAndIbanAndMoveType(userId,iban,move);
    }
}
