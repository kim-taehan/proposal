package org.developx.proposal.web.user.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import org.developx.proposal.domain.user.data.request.CreateUserRequest;

public record CreateUserForm (
    @NotBlank
    String username,
    @NotBlank
    String password,
    @NotBlank
    String realName,
    @NotNull
    @Positive
    Long teamId
)
{
    @Builder
    public CreateUserForm(String username,
                          String password,
                          String realName,
                          Long teamId) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.teamId = teamId;
    }

    public CreateUserRequest toCreateUserRequest() {
        return CreateUserRequest.builder()
                .username(username)
                .password(password)
                .realName(realName)
                .teamId(teamId)
                .build();
    }

    public static CreateUserForm getInstance() {
        return new CreateUserForm("", "", "", null);
    }
}
