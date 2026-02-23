package come.back.domain.settlement.settlement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import come.back.domain.settlement.settlement.entity.SettlementTransaction;

public interface SettlementTransactionRepository extends JpaRepository<SettlementTransaction, Long> {
    List<SettlementTransaction> findByFromMember_Member_IdOrToMember_Member_Id(Long fromMemberId, Long toMemberId);
}
