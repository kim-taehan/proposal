package org.developx.proposal.domain.proposal.service;

import org.developx.proposal.domain.proposal.data.ContextDto;
import org.developx.proposal.domain.proposal.data.Contexts;
import org.developx.proposal.domain.proposal.entity.Context;
import org.springframework.data.domain.Page;

public interface ContextService {
    Context findById(Long contextId);

    Page<Contexts> findContexts(String text);

}
