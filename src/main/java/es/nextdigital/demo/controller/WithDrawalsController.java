package es.nextdigital.demo.controller;

import es.nextdigital.demo.service.BankCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank/withdrawals")
@Slf4j
public class WithDrawalsController {

    private final BankCardService bankCardService;

    public WithDrawalsController(BankCardService bankCardService) {
        this.bankCardService = bankCardService;
    }


    @GetMapping("/cardNumber/{cardNumber}/amount/{amount}")
    public ResponseEntity<?> withDrawals(@PathVariable Long cardNumber,@PathVariable Long amount ){

        return ResponseEntity.ok().body(this.bankCardService.withDrawals(cardNumber,amount));
    }
}
