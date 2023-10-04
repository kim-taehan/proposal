package org.developx.proposal.web.user.data;

import lombok.Builder;
import org.developx.proposal.domain.user.data.request.FindUsersRequest;

public record UsersForm(String realName, Long teamId) {

    @Builder
    public UsersForm(String realName, Long teamId) {
        this.realName = realName;
        this.teamId = teamId;
    }

    public FindUsersRequest toFindUserRequest() {
        return FindUsersRequest.builder()
                .realName(realName)
                .teamId(teamId)
                .build();
    }

}
