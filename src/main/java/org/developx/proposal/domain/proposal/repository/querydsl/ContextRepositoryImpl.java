package org.developx.proposal.domain.proposal.repository.querydsl;

import org.developx.proposal.domain.proposal.entity.Context;
import org.developx.proposal.global.infra.Querydsl4RepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.developx.proposal.domain.proposal.entity.QContext.context;

public class ContextRepositoryImpl extends Querydsl4RepositorySupport implements ContextRepositoryQueryDsl {

    public ContextRepositoryImpl() {
        super(Context.class);
    }

    @Override
    public Page<Context> findContexts(String text, Pageable pageable) {
        return applyPagination(pageable,
                contentQuery -> contentQuery
                        .selectFrom(context)
                        .where(
                                context.text.like("%" + text + "%")
                        )
        );
    }
}
