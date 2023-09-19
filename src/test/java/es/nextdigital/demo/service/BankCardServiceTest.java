package es.nextdigital.demo.service;

import es.nextdigital.demo.repository.BankCardRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BankCardServiceTest {

    @InjectMocks
    private BankCardService bankCardService;

    @Mock
    private BankCardRepository bankCardRepository;

    @Test
    @DisplayName("Test 1 - WithDrawals correction execution")
    public void withDrawalsTestOk(){

        Boolean executionRes = this.bankCardService.withDrawals(1111L,1234L);
        Assertions.assertTrue(executionRes);

    }

    @Test
    @DisplayName("Test 2 - WithDrawals not correction execution")
    public void withDrawalsTestKo(){

        Boolean executionRes = this.bankCardService.withDrawals(0L,1234L);
        Assertions.assertTrue(executionRes);

    }

    @Test
    @DisplayName("Test 3 - Deposit correction execution")
    public void depositTestOk(){

        Boolean executionRes = this.bankCardService.deposit(1111L,1234L, "My bank");
        Assertions.assertTrue(executionRes);

    }

    @Test
    @DisplayName("Test 4 - Deposit not correction execution")
    public void depositTestKo(){

        Boolean executionRes = this.bankCardService.deposit(1111L,1234L, "");
        Assertions.assertTrue(executionRes);

    }

    @Test
    @DisplayName("Test 5 - Active correction execution")
    public void activeTestOk(){

        Boolean executionRes = this.bankCardService.active(1111L);
        Assertions.assertTrue(executionRes);

    }

    @Test
    @DisplayName("Test 6 - Active not correction execution")
    public void activeTestKo(){

        Boolean executionRes = this.bankCardService.active(0L);
        Assertions.assertTrue(executionRes);

    }
}
