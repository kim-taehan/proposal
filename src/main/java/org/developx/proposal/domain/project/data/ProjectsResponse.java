package org.developx.proposal.domain.project.data;

import org.developx.proposal.domain.project.entity.Project;

public record ProjectsResponse (
        long id,
        String projectName,
        String customer
){
    public static ProjectsResponse from(Project project) {
        return new ProjectsResponse(
                project.getId(),
                project.getProjectName(),
                project.getCustomer()
        );
    }
}
