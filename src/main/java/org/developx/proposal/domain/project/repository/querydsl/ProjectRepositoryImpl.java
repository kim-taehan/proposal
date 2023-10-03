package org.developx.proposal.domain.project.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.developx.proposal.domain.customer.data.enums.CustomerType;
import org.developx.proposal.domain.project.data.request.FindProjectRequest;
import org.developx.proposal.domain.project.entity.Project;
import org.developx.proposal.global.infra.Querydsl4RepositorySupport;
import org.developx.proposal.global.utils.QueryDslUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import static org.developx.proposal.domain.customer.entity.QCustomer.customer;
import static org.developx.proposal.domain.project.entity.QProject.project;

public class ProjectRepositoryImpl extends Querydsl4RepositorySupport implements ProjectRepositoryQueryDsl {
    public ProjectRepositoryImpl() {
        super(Project.class);
    }
    @Override
    public Page<Project> findProjects(FindProjectRequest request, Pageable pageable) {
        return applyPagination(pageable,
                contentQuery -> contentQuery
                        .selectFrom(project)
                        .where(
                                projectNameLike(request.projectName())
                        )
        );
    }

    private static BooleanExpression projectNameLike(String projectName) {
        return StringUtils.hasText(projectName) ? project.projectName.like(QueryDslUtils.makeLikeText(projectName)) : null;
    }

    private static BooleanExpression customerTypeEq(CustomerType customerType) {
        return ObjectUtils.isEmpty(customerType) ? null : customer.customerType.eq(customerType);
    }
}
