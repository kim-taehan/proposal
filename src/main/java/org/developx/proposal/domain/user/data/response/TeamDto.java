package org.developx.proposal.domain.user.data.response;

import org.developx.proposal.domain.user.entity.Team;

public record TeamDto(Long teamId, String teamName) {
    public static TeamDto from(Team team) {
        return new TeamDto(team.getId(), team.getTeamName());
    }
}
