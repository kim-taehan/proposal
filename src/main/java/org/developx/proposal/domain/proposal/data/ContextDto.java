package org.developx.proposal.domain.proposal.data;

import org.developx.proposal.domain.proposal.entity.Context;

import java.nio.file.Path;

public record ContextDto (
        long contextId,
        int slideNumber,

        Path pullPath
)
{
    public static ContextDto from(Context context) {

        return new ContextDto(context.getId(),
                context.getSlide().getSlideNumber(),
                context.getSlide().getDocument().getPath()
        );
    }
}
