package org.developx.proposal.domain.proposal.repository;

import org.developx.proposal.domain.project.entity.Project;
import org.developx.proposal.domain.proposal.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {

}
