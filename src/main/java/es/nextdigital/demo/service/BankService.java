package es.nextdigital.demo.service;

import es.nextdigital.demo.repository.BankRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankService {

    private final BankRepository bankRepository;


    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }
}
