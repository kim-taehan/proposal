package org.developx.proposal.domain.bookmark.service.impl;

import lombok.RequiredArgsConstructor;
import org.developx.proposal.domain.bookmark.entity.Bookmark;
import org.developx.proposal.domain.bookmark.repository.BookmarkRepository;
import org.developx.proposal.domain.bookmark.service.BookmarkService;
import org.developx.proposal.domain.proposal.entity.Slide;
import org.developx.proposal.domain.proposal.service.SlideService;
import org.developx.proposal.domain.user.entity.User;
import org.developx.proposal.domain.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UserService userService;

    public Bookmark findById(long bookmarkId) {
        return bookmarkRepository.findById(bookmarkId).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    @Transactional
    public void addBookmark(Slide slide) {
        User loginUser = userService.findLoginUser();

        Bookmark newBookmark = Bookmark.builder()
                .user(loginUser)
                .slide(slide)
                .build();
        bookmarkRepository.save(newBookmark);
    }

//    @Override
//    @Transactional
//    public long addBookmark(String username, long slideId, String comment) {
//
//        User user = userService.findByUsername(username);
//        Slide slide = slideService.findById(slideId);
//
//        Bookmark newBookmark = Bookmark.builder()
//                .user(user)
//                .slide(slide)
//                .comment(comment)
//                .build();
//        bookmarkRepository.save(newBookmark);
//
//        return newBookmark.getId();
//    }



    @Override
    @Transactional
    public void removeBookmark(long bookmarkId) {
        Bookmark bookmark = findById(bookmarkId);
        bookmarkRepository.delete(bookmark);
    }

    @Override
    public long findBookmarkId(Slide slide) {
        return bookmarkRepository.findByUserAndSlide(userService.findLoginUser(), slide)
                .map(bookmark -> bookmark.getId())
                .orElse(0L);
    }



}
