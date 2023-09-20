package org.developx.proposal.domain.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.developx.proposal.domain.user.data.enums.TeamType;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    @Column(length = 100)
    private String teamName;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private TeamType teamType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_team_id")
    private Team parent;

    @Builder
    public Team(String teamName, TeamType teamType, Team parent) {
        this.teamName = teamName;
        this.teamType = teamType;
        this.parent = parent;
    }
}
