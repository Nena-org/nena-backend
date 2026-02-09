package come.back.domain.settlement.settlement.entity;

import come.back.domain.settlementRoom.settlementRoom.entity.SettlementRoom;
import come.back.global.jpa.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Settlement extends BaseEntity {

    @ManyToOne
    private SettlementRoom settlementRoom;
}
