package come.back.domain.member.member.dto;

import come.back.domain.settlementRoom.settlementRoom.entity.RoomMember;

import java.time.LocalDateTime;

public record SettlementListResponseDto(
        boolean isCompleted,
        LocalDateTime completedAt,
        RoomMember fromMember,
        RoomMember toMember,
        long totalAmount
) {
}
