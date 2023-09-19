package es.nextdigital.demo.controller;

import es.nextdigital.demo.dto.in.ConsultingInDTO;
import es.nextdigital.demo.service.BankAccountService;
import es.nextdigital.demo.service.BankingMovementService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank/consulting")
@Slf4j
public class ConsultingController {

    private final BankingMovementService bankingMovementService;

    private final BankAccountService bankAccountService;


    public ConsultingController(BankingMovementService bankingMovementService, BankAccountService bankAccountService) {
        this.bankingMovementService = bankingMovementService;
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public ResponseEntity<?> consulting(@Valid @RequestBody ConsultingInDTO consultingInDTO){

        return ResponseEntity.ok().body( this.bankAccountService.
                findByUserIdAndIbanAndMoveType(consultingInDTO.getUserId(),consultingInDTO.getIban(),consultingInDTO.getMove()));

    }


}
