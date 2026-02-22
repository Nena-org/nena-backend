package come.back.domain.settlement.settlement.repository;

import come.back.domain.settlement.settlement.entity.SettlementTransaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SettlementTransactionRepository extends JpaRepository<Long, SettlementTransaction> {
    List<SettlementTransaction>  findByFromMember_Member_IdOrToMember_Member_Id(Long fromMemberId, Long toMemberId);
}
