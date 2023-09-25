package org.developx.proposal.domain.proposal.repository;

import org.developx.proposal.domain.proposal.entity.Document;
import org.developx.proposal.domain.proposal.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

}
