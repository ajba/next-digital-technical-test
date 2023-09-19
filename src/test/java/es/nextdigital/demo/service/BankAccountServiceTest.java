package es.nextdigital.demo.service;

import es.nextdigital.demo.dto.out.ConsultingOutDTO;
import es.nextdigital.demo.repository.BankAccountRepository;
import es.nextdigital.demo.repository.BankCardRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BankAccountServiceTest {

    @InjectMocks
    private BankAccountService bankAccountService;

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Test
    @DisplayName("Test 1 - findByUserIdAndIbanAndMoveType correction execution")
    public void findByUserIdAndIbanAndMoveTypeOk(){

        List<ConsultingOutDTO> executionRes = this.bankAccountService.findByUserIdAndIbanAndMoveType(1L,"ES9990230203020","TRANSFER");
        Assertions.assertTrue(executionRes.size() >= 1);

    }

    @Test
    @DisplayName("Test 1 - findByUserIdAndIbanAndMoveType not correction execution")
    public void findByUserIdAndIbanAndMoveTypeKo(){

        List<ConsultingOutDTO> executionRes = this.bankAccountService.findByUserIdAndIbanAndMoveType(1L,"","");
        Assertions.assertTrue(executionRes.size() >= 1);

    }

}
