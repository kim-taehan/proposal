package org.developx.proposal.domain.project.data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectForm {

    @NotEmpty
    private String projectName;

    @NotEmpty
    private String customer;
}
