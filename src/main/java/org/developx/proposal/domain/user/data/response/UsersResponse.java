package org.developx.proposal.domain.user.data.response;

import org.developx.proposal.domain.user.entity.User;
import org.developx.proposal.global.infra.jpa.BaseData;

public record UsersResponse(
        Long userId,
        String username,
        String realName,
        BaseData baseData,
        TeamDto team
) {
    public static UsersResponse from(User user) {
        return new UsersResponse(
                user.getId(),
                user.getUsername(),
                user.getRealName(),
                BaseData.from(user),
                TeamDto.from(user.getTeam())
        );
    }
}