package org.developx.proposal.domain.proposal.repository;

import org.developx.proposal.domain.proposal.entity.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlideRepository extends JpaRepository<Slide, Long> {

}
