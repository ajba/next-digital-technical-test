package es.nextdigital.demo.service;

import es.nextdigital.demo.domain.BankCard;
import es.nextdigital.demo.repository.BankCardRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
@Transactional
public class BankCardService {

    private final BankCardRepository bankCardRepository;


    public BankCardService(BankCardRepository bankCardRepository) {
        this.bankCardRepository = bankCardRepository;
    }

    public Boolean withDrawals(Long cardNumber, Long amount) {
        BankCard bankCard = this.findByCardNumber(cardNumber);
        Boolean success = false;

        if(bankCard != null && bankCard.getIsActive()){
            if(bankCard.getCardType().toString().equals("DEBIT") && bankCard.getBankAccount().getBalance() >= amount){
                bankCard.getBankAccount().setBalance(bankCard.getBankAccount().getBalance()-amount);
                success = true;
            }else if(bankCard.getCardType().toString().equals("CREDIT")){
                bankCard.getBankAccount().setBalance(bankCard.getBankAccount().getBalance()-amount);
                success = true;
            }
        }
        this.save(bankCard);

        return success;
    }

    public BankCard findByCardNumber(Long cardNumber){
        return this.bankCardRepository.findByNumber(cardNumber);
    }

    public BankCard save(BankCard bankCard){
        return this.bankCardRepository.save(bankCard);
    }

    public Boolean deposit(Long cardNumber, Long amount, String cashierName) {
        BankCard bankCard = this.findByCardNumber(cardNumber);
        Boolean success = false;

        if(bankCard != null && bankCard.getIsActive() && bankCard.getBankAccount().getBank().getName().equals(cashierName)){
            bankCard.getBankAccount().setBalance(bankCard.getBankAccount().getBalance()+amount);
            success = true;
        }
        this.save(bankCard);

        return success;
    }

    public Boolean active(Long cardNumber) {
        BankCard bankCard = this.findByCardNumber(cardNumber);
        Boolean success = false;

        if(bankCard != null) {
            bankCard.setIsActive(true);
            this.bankCardRepository.save(bankCard);
            success = true;
        }

        return success;
    }

    public Boolean changePin(Long cardNumber, String pin) {
        BankCard bankCard = this.findByCardNumber(cardNumber);
        Boolean success = false;

        if(bankCard != null) {
            String encriptPin= String.valueOf(DigestUtils.md5Digest(pin.getBytes(StandardCharsets.UTF_8)));
            bankCard.setPin(encriptPin);
            this.bankCardRepository.save(bankCard);
            success = true;

        }

        return success;
    }
}
