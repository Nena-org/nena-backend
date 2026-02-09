package come.back.domain.settlementRoom.settlementRoom.entity;

import come.back.domain.member.member.entity.Member;
import come.back.global.jpa.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class RoomMember extends BaseEntity {
    @ManyToOne
    private SettlementRoom settlementRoom;

    @ManyToOne
    private Member member;
}
