package org.developx.proposal.domain.user.repository;

import org.developx.proposal.domain.user.entity.Privacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivacyRepository extends JpaRepository<Privacy, Long> {
}
