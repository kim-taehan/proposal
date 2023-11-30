package org.developx.proposal.domain.bookmark.repository;

import org.developx.proposal.domain.bookmark.entity.Bookmark;
import org.developx.proposal.domain.customer.entity.Customer;
import org.developx.proposal.domain.customer.repostory.querydsl.CustomerRepositoryQueryDsl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

}
