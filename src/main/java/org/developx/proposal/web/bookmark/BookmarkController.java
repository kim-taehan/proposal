package org.developx.proposal.web.bookmark;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.developx.proposal.domain.bookmark.service.BookmarkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@Slf4j
@RequestMapping("/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("{slideId}")
    public String addBookmark(@PathVariable long slideId, String comment, Model model, Principal principal) {

        String username = principal.getName();
        log.info("username={}", username);

        bookmarkService.addBookmark(username, slideId, comment);

        model.addAttribute("bookmarkYn", "y");
        return "/proposals/preview :: bookmarkButton"; // template 파일 이름 + '::' + 데이터가 변경 될
    }
}
