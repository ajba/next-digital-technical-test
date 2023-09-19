package es.nextdigital.demo.controller;

import es.nextdigital.demo.service.BankCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank/withdrawals")
@Slf4j
public class DepositController {

    private final BankCardService bankCardService;

    public DepositController(BankCardService bankCardService) {
        this.bankCardService = bankCardService;
    }


    @GetMapping("/cardNumber/{cardNumber}/amount/{amount}/cashierName/{cashierName}")
    public ResponseEntity<?> deposit(@PathVariable Long cardNumber, @PathVariable Long amount, @PathVariable String cashierName ){

        return ResponseEntity.ok().body(this.bankCardService.deposit(cardNumber,amount,cashierName));
    }
}
