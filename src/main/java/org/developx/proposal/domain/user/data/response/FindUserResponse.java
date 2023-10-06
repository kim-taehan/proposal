package org.developx.proposal.domain.user.data.response;

import lombok.Builder;
import org.developx.proposal.domain.user.entity.User;
import org.developx.proposal.domain.user.entity.enums.Role;
import org.developx.proposal.global.infra.jpa.BaseData;

public record FindUserResponse(
        Long userId,
        String username,
        String realName,
        Role role,
        TeamDto team,
        PrivacyDto privacy,
        BaseData baseData
) {
    @Builder
    public FindUserResponse(Long userId, String username, String realName, Role role, TeamDto team, PrivacyDto privacy, BaseData baseData) {
        this.userId = userId;
        this.username = username;
        this.realName = realName;
        this.role = role;
        this.team = team;
        this.privacy = privacy;
        this.baseData = baseData;
    }

    public static FindUserResponse from(User user) {
        return new FindUserResponse(
                user.getId(),
                user.getUsername(),
                user.getRealName(),
                user.getRole(),
                TeamDto.from(user.getTeam()),
                PrivacyDto.from(user.getPrivacy()),
                BaseData.from(user)
        );
    }
}
