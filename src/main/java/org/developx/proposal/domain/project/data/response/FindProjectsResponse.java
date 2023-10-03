package org.developx.proposal.domain.project.data.response;

import org.developx.proposal.domain.customer.data.response.FindCustomersResponse;
import org.developx.proposal.domain.project.entity.Project;

public record FindProjectsResponse(
        long projectId,
        String projectName,
        String projectYear,
        FindCustomersResponse customer
){
    public static FindProjectsResponse from(Project project) {
        return new FindProjectsResponse(
                project.getId(),
                project.getProjectName(),
                project.getProjectYear(),
                FindCustomersResponse.from(project.getCustomer())
        );
    }
}
