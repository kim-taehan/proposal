package org.developx.proposal.domain.proposal.data;

import org.developx.proposal.domain.bookmark.entity.Bookmark;
import org.developx.proposal.domain.proposal.entity.Context;

import java.nio.file.Path;

public record ContextDto (
        long contextId,
        long slideId,
        int slideNumber,
        Path pullPath,
        long bookmarkId
)
{
    public static ContextDto from(Context context, long bookmarkId) {
        return new ContextDto(
                context.getId(),
                context.getSlide().getId(),
                context.getSlide().getSlideNumber(),
                context.getSlide().getDocument().getPath(),
                bookmarkId
        );
    }


}
