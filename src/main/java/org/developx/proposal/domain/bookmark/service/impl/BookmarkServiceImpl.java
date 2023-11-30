package org.developx.proposal.domain.bookmark.service.impl;

import lombok.RequiredArgsConstructor;
import org.developx.proposal.domain.bookmark.entity.Bookmark;
import org.developx.proposal.domain.bookmark.repository.BookmarkRepository;
import org.developx.proposal.domain.bookmark.service.BookmarkService;
import org.developx.proposal.domain.customer.entity.Customer;
import org.developx.proposal.domain.customer.service.CustomerService;
import org.developx.proposal.domain.proposal.entity.Slide;
import org.developx.proposal.domain.proposal.service.SlideService;
import org.developx.proposal.domain.user.entity.User;
import org.developx.proposal.domain.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UserService userService;
    private final SlideService slideService;

    @Override
    @Transactional
    public void addBookmark(String username, long slideId, String comment) {

        User user = userService.findByUsername(username);
        Slide slide = slideService.findById(slideId);

        Bookmark newBookmark = Bookmark.builder()
                .user(user)
                .slide(slide)
                .comment(comment)
                .build();
        bookmarkRepository.save(newBookmark);
    }
}
