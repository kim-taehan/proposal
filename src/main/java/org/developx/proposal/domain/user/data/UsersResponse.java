package org.developx.proposal.domain.user.data;


import org.developx.proposal.domain.user.entity.User;
import org.developx.proposal.global.infra.jpa.BaseData;
import org.developx.proposal.global.infra.jpa.BaseEntity;

import java.time.LocalDateTime;

public record UsersResponse(
        Long id,
        String username,
        String realName,
        BaseData baseData
) {
    public static UsersResponse from(User user) {
        return new UsersResponse(
                user.getId(),
                user.getUsername(),
                user.getRealName(),
                BaseData.from(user)
        );
    }
}