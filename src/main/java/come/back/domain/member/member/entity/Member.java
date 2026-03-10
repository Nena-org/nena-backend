package come.back.domain.member.member.entity;

import come.back.global.jpa.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String tossUserKey;

    public Member(String tossUserKey) {
        this.tossUserKey = tossUserKey;
    }
}
