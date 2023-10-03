package org.developx.proposal.web.project.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.developx.proposal.domain.project.data.request.CreateProjectRequest;

@Data
public class CreateProjectForm {

    @NotBlank
    private String projectName;

    @NotBlank
    private String projectYear;

    @NotNull
    private Long customerId;

    public CreateProjectRequest toCreateProjectRequest() {
        return CreateProjectRequest.builder()
                .projectName(projectName)
                .projectYear(projectYear)
                .customerId(customerId)
                .build();
    }
}
