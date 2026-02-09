package come.back.domain.payment.payment.entity;

import come.back.domain.settlementRoom.settlementRoom.entity.RoomMember;
import come.back.global.jpa.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class PaymentMember extends BaseEntity {
    private long shareAmount;

    @ManyToOne
    private Payment Payment;

    @ManyToOne
    private RoomMember RoomMember;
}
