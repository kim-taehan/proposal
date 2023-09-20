package org.developx.proposal.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.developx.proposal.global.infra.jpa.BaseEntity;

import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    /* spring security 필드와 통일성을 유지하기 위해 사번을 username 으로 명명함 */
    @Column(unique=true, length = 20)
    private String username;
    
    /* spring security */
    @Column(length = 100)
    private String password;

    @Column(unique=true, length = 20)
    private String realName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder
    public User(String username, String password, String realName, Team team) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.team = team;
    }
}
