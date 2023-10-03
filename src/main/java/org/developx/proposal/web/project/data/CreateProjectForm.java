package org.developx.proposal.web.project.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.developx.proposal.domain.project.data.request.CreateProjectRequest;

public record CreateProjectForm (
    @NotBlank String projectName,
    @NotBlank String projectYear,
    @NotNull Long customerId
){

    public static CreateProjectForm getInstance() {
        return new CreateProjectForm("", "", null);
    }
    public CreateProjectRequest toCreateProjectRequest() {
        return CreateProjectRequest.builder()
                .projectName(projectName)
                .projectYear(projectYear)
                .customerId(customerId)
                .build();
    }
}
