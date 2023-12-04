package org.developx.proposal.domain.bookmark.service;

import lombok.extern.slf4j.Slf4j;
import org.developx.proposal.SpringBootTestSupport;
import org.developx.proposal.domain.project.service.ProjectService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@DisplayName("[service] project service")
class BookmarkServiceTest extends SpringBootTestSupport {
    @Autowired
    private BookmarkService bookmarkService;
    @Test
    void addBookmark() {
        bookmarkService.removeBookmark(1);
        log.info("add bookmark");
    }

    @Test
    void removeBookmark() {
    }
}