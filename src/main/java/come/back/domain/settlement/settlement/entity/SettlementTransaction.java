package come.back.domain.settlement.settlement.entity;

import java.time.LocalDateTime;

import come.back.domain.settlementRoom.settlementRoom.entity.RoomMember;
import come.back.global.jpa.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class SettlementTransaction extends BaseEntity {
    @ManyToOne
    private Settlement settlement;

    @ManyToOne
    private RoomMember fromMember;

    @ManyToOne
    private RoomMember toMember;

    private long totalAmount;

    private boolean isCompleted;

    private LocalDateTime completedAt;
}
