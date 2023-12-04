package org.developx.proposal.domain.bookmark.repository;

import org.developx.proposal.domain.bookmark.entity.Bookmark;
import org.developx.proposal.domain.proposal.entity.Slide;
import org.developx.proposal.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Optional<Bookmark> findByUserAndSlide(User user, Slide slide);
}
