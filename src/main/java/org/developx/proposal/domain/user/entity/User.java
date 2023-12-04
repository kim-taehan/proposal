package org.developx.proposal.domain.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.developx.proposal.domain.user.entity.enums.Role;
import org.developx.proposal.global.infra.jpa.BaseEntity;

import java.util.regex.Pattern;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "users")
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

    @Column(unique=true, length = 20, nullable = false)
    private String realName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Role role;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "privacy_id")
    private Privacy privacy;

    @Builder
    public User(String username, String password, String realName, Team team, Role role, Privacy privacy) {
        if (!checkUsernameValidation(username)) {
            throw new IllegalArgumentException("username 은 숫자만 5자리를 입력받아야 합니다.");
        }
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.team = team;
        this.role = role;
        this.privacy = privacy;
    }

    private static boolean checkUsernameValidation(String username) {
        return username.length() == 5 && Pattern.matches("^[\\d]*$", username);
    }

}
