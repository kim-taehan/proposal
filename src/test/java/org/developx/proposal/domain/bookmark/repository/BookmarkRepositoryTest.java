package org.developx.proposal.domain.bookmark.repository;

import org.developx.proposal.DataJpaTestSupport;
import org.developx.proposal.domain.bookmark.entity.Bookmark;
import org.developx.proposal.domain.proposal.entity.Slide;
import org.developx.proposal.domain.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
@DisplayName("[repository] bookmark repository")
class BookmarkRepositoryTest extends DataJpaTestSupport {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @DisplayName("User와 slide 정보로 Bookmark 여부를 확인할 수 있다.")
    @Test
    void findByUserAndSlide(){
        // given
        User userA = User.builder().username("00001").realName("00001").build();
        User userB = User.builder().username("00002").realName("00002").build();
        Slide slide = Slide.builder().slideNumber(1).build();

        entityManager.persist(userA);
        entityManager.persist(userB);
        entityManager.persist(slide);

        Bookmark bookmarkA = Bookmark.builder()
                .slide(slide)
                .user(userA)
                .build();

        Bookmark bookmarkB = Bookmark.builder()
                .slide(slide)
                .user(userB)
                .build();

        bookmarkRepository.save(bookmarkA);
        bookmarkRepository.save(bookmarkB);

        entityManager.flush();
        entityManager.clear();

        // when
        Bookmark bookmark = bookmarkRepository.findByUserAndSlide(userA, slide)
                .orElseThrow(() -> new IllegalArgumentException());

        // then
        assertThat(bookmark.getId()).isEqualTo(bookmarkA.getId());
    }


}