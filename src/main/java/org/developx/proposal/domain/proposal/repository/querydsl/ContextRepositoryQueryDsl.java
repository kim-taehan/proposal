package org.developx.proposal.domain.proposal.repository.querydsl;

import org.developx.proposal.domain.proposal.entity.Context;
import org.developx.proposal.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContextRepositoryQueryDsl {
    Page<Context> findContexts(String text, Pageable pageable);
}
