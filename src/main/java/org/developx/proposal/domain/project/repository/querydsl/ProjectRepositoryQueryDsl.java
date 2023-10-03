package org.developx.proposal.domain.project.repository.querydsl;

import org.developx.proposal.domain.project.data.request.FindProjectRequest;
import org.developx.proposal.domain.project.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectRepositoryQueryDsl {
    Page<Project> findProjects(FindProjectRequest request, Pageable pageable);
}
