package come.back.domain.member.member.dto;

import come.back.domain.settlement.settlement.entity.SettlementTransaction;
import come.back.domain.settlementRoom.settlementRoom.entity.RoomMember;

import java.time.LocalDateTime;

public record SettlementListResponseDto(
        boolean isCompleted,
        LocalDateTime completedAt,
        Long fromMemberId,
        Long toMemberId,
        long totalAmount
) {
    public static SettlementListResponseDto from(SettlementTransaction tx) {
        return new SettlementListResponseDto(
                tx.isCompleted(),
                tx.getCompletedAt(),
                tx.getFromMember().getId(),
                tx.getToMember().getId(),
                tx.getTotalAmount()
        );
    }
}
