package es.nextdigital.demo.service;

import es.nextdigital.demo.repository.BankCardRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankCardService {

    private final BankCardRepository bankCardRepository;


    public BankCardService(BankCardRepository bankCardRepository) {
        this.bankCardRepository = bankCardRepository;
    }
}
