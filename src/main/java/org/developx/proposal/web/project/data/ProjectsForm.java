package org.developx.proposal.web.project.data;

import org.developx.proposal.domain.project.data.request.FindProjectRequest;

public record ProjectsForm (String projectName){

    public FindProjectRequest toFindProjectRequest() {
        return new FindProjectRequest(projectName);
    }
}
