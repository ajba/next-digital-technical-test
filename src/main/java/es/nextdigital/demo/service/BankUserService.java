package es.nextdigital.demo.service;

import es.nextdigital.demo.repository.BankUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankUserService {

    private final BankUserRepository bankUserRepository;


    public BankUserService(BankUserRepository bankUserRepository) {
        this.bankUserRepository = bankUserRepository;
    }
}
