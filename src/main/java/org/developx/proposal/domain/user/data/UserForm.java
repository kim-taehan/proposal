package org.developx.proposal.domain.user.data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserForm {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String realName;

    @NotNull
    private long teamId;


}
