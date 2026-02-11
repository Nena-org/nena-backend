package come.back.domain.payment.payment.entity;

import come.back.domain.settlement.settlement.entity.Settlement;
import come.back.domain.settlementRoom.settlementRoom.entity.RoomMember;
import come.back.global.jpa.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Payment extends BaseEntity {
    private long amount;

    @ManyToOne
    private RoomMember payedMember;

    @ManyToOne
    private Settlement settlement;
}
