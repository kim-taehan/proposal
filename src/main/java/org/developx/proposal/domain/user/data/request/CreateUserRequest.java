package org.developx.proposal.domain.user.data.request;

import lombok.Builder;
import org.developx.proposal.domain.user.entity.Team;
import org.developx.proposal.domain.user.entity.User;

public record CreateUserRequest(String username, String password, String realName, long teamId) {

    @Builder
    public CreateUserRequest(String username, String password, String realName, long teamId) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.teamId = teamId;
    }

    public User toEntity(Team team) {
        return User.builder()
                .username(username)
                .password(password)
                .realName(realName)
                .team(team)
                .build();
    }
}
