package es.nextdigital.demo.controller;

import es.nextdigital.demo.service.BankCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank/card-bank")
@Slf4j
public class CardBankController {

    private final BankCardService bankCardService;

    public CardBankController(BankCardService bankCardService) {
        this.bankCardService = bankCardService;
    }


    @GetMapping("active/cardNumber/{cardNumber}")
    public ResponseEntity<?> active(@PathVariable Long cardNumber){

        return ResponseEntity.ok().body(this.bankCardService.active(cardNumber));
    }

    @GetMapping("change-pin/cardNumber/{cardNumber}/pin/{pin}")
    public ResponseEntity<?> deposit(@PathVariable Long cardNumber,@PathVariable String pin){

        return ResponseEntity.ok().body(this.bankCardService.changePin(cardNumber,pin));
    }
}
