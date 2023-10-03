package org.developx.proposal.domain.project.data.request;

import lombok.Builder;

public record CreateProjectRequest(
        String projectName,
        String projectYear,
        long customerId
) {

    @Builder
    public CreateProjectRequest(String projectName, String projectYear, long customerId) {
        this.projectName = projectName;
        this.projectYear = projectYear;
        this.customerId = customerId;
    }
}
