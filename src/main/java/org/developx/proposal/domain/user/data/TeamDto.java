package org.developx.proposal.domain.user.data;

import org.developx.proposal.domain.user.entity.Team;

public record TeamDto(Long id, String teamName) {
    public static TeamDto from(Team team) {
        return new TeamDto(team.getId(), team.getTeamName());
    }
}
