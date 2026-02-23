package come.back.domain.member.member.dto;

import java.time.LocalDateTime;

import come.back.domain.settlement.settlement.entity.SettlementTransaction;

public record SettlementListResponseDto(
        boolean isCompleted, LocalDateTime completedAt, Long fromMemberId, Long toMemberId, long totalAmount) {
    public static SettlementListResponseDto from(SettlementTransaction tx) {
        return new SettlementListResponseDto(
                tx.isCompleted(),
                tx.getCompletedAt(),
                tx.getFromMember().getMember().getId(),
                tx.getToMember().getMember().getId(),
                tx.getTotalAmount());
    }
}
