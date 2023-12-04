package org.developx.proposal.domain.proposal.service;

import lombok.RequiredArgsConstructor;
import org.developx.proposal.domain.bookmark.service.BookmarkService;
import org.developx.proposal.domain.proposal.data.ContextDto;
import org.developx.proposal.domain.proposal.data.Contexts;
import org.developx.proposal.domain.proposal.entity.Context;
import org.developx.proposal.domain.proposal.repository.ContextRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ContextServiceImpl implements ContextService {

    private final ContextRepository contextRepository;
    private final BookmarkService bookmarkService;

    @Override
    public Context findById(Long contextId) {
        return contextRepository.findById(contextId).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public Page<Contexts> findContexts(String text) {
        Page<Context> all = contextRepository.findContexts(text, Pageable.ofSize(10));
        return all.map(context -> Contexts.from(context));
    }

}
