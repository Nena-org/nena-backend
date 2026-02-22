package come.back.domain.member.member.service;

import come.back.domain.member.member.dto.SettlementListResponseDto;
import come.back.domain.settlement.settlement.entity.SettlementTransaction;
import come.back.domain.settlement.settlement.repository.SettlementTransactionRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final SettlementTransactionRepository settlementTransactionRepository;

    public List<SettlementListResponseDto> getMySettlements(Long userId) {
        List<SettlementTransaction> transactions = settlementTransactionRepository.findByFromMember_Member_IdOrToMember_Member_Id(userId, userId);
        return transactions.stream()
                .map(SettlementListResponseDto::from)
                .toList();
    }
}
