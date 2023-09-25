package org.developx.proposal.domain.project.repository.querydsl;

import org.developx.proposal.domain.project.entity.Project;
import org.developx.proposal.global.infra.Querydsl4RepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.developx.proposal.domain.project.entity.QProject.project;

public class ProjectRepositoryImpl extends Querydsl4RepositorySupport implements ProjectRepositoryQueryDsl {
    public ProjectRepositoryImpl() {
        super(Project.class);
    }
    @Override
    public Page<Project> findProjects(Pageable pageable) {
        return applyPagination(pageable,
                contentQuery -> contentQuery
                        .selectFrom(project)
                        .where(
                        )
        );
    }
}
