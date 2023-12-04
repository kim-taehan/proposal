package org.developx.proposal.domain.bookmark.service;

import org.developx.proposal.domain.proposal.entity.Slide;

public interface BookmarkService {

    void removeBookmark(long bookmarkId);

    long findBookmarkId(Slide slide);

    void addBookmark(Slide slide);
}
