package org.developx.proposal.domain.user.data.request;

import lombok.Builder;

public record FindUsersRequest(String realName, Long teamId) {
    @Builder
    public FindUsersRequest(String realName, Long teamId) {
        this.realName = realName;
        this.teamId = teamId;
    }
}
