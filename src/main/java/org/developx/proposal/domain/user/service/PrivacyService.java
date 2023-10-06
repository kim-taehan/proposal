package org.developx.proposal.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.developx.proposal.domain.user.entity.Privacy;
import org.developx.proposal.domain.user.repository.PrivacyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PrivacyService {

    private final PrivacyRepository privacyRepository;

    public Privacy findById(Long privacyId) {
        return privacyRepository.findById(privacyId)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    @Transactional
    public Privacy save(Privacy privacy) {
        return privacyRepository.save(privacy);
    }
}
