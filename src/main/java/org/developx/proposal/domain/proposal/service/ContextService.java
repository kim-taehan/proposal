package org.developx.proposal.domain.proposal.service;

import lombok.RequiredArgsConstructor;
import org.developx.proposal.domain.proposal.data.ContextDto;
import org.developx.proposal.domain.proposal.data.Contexts;
import org.developx.proposal.domain.proposal.entity.Context;
import org.developx.proposal.domain.proposal.repository.ContextRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContextService {

    private final ContextRepository contextRepository;

    public Page<Contexts> findContexts(String text) {
        Page<Context> all = contextRepository.findContexts(text, Pageable.ofSize(10));
        return all.map(context -> Contexts.from(context));
    }

    public ContextDto findContext(Long contextId) {

        Context context = contextRepository.findById(contextId).orElseThrow(()->new IllegalArgumentException());

        return ContextDto.from(context);
    }
}
