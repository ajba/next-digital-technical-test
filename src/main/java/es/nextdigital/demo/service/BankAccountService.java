package es.nextdigital.demo.service;

import es.nextdigital.demo.domain.BankAccount;
import es.nextdigital.demo.dto.out.ConsultingOutDTO;
import es.nextdigital.demo.repository.BankAccountRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    private final ModelMapper modelMapper;

    public BankAccountService(BankAccountRepository bankAccountRepository, ModelMapper modelMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.modelMapper = modelMapper;
    }

    public List<ConsultingOutDTO> findByUserIdAndIbanAndMoveType(Long userId, String iban, String move) {
        BankAccount bankAccount = this.bankAccountRepository.findByUserIdAndIbanAndMoveType(userId,iban,move);
        List<ConsultingOutDTO> consultingOutDTORes = new ArrayList<>();

        if(bankAccount != null){
            consultingOutDTORes = bankAccount.getBankingMovements().stream().map(bm -> new ConsultingOutDTO(bm.getId(),bm.getMove().toString())).collect(Collectors.toList());
        }else {
            return consultingOutDTORes;
        }
    }
}
