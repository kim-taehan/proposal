package org.developx.proposal.web.bookmark;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.developx.proposal.domain.bookmark.service.BookmarkService;
import org.developx.proposal.domain.proposal.entity.Slide;
import org.developx.proposal.domain.proposal.service.SlideService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final SlideService slideService;
    private final BookmarkService bookmarkService;

    @PostMapping("add")
    public String addBookmark(Long slideId, Model model) {
        Slide slide = slideService.findById(slideId);
        bookmarkService.addBookmark(slide);
        model.addAttribute("slideId", slideId);
        return "redirect:/slides/" + slideId;
    }
    @PostMapping("remove")
    public String removeBookmark(Long slideId, Long bookmarkId, Model model) {
        Slide slide = slideService.findById(slideId);
        bookmarkService.removeBookmark(bookmarkId);
        model.addAttribute("slideId", slideId);
        return "redirect:/slides/" + slideId;
    }
}
