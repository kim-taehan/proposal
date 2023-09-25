package org.developx.proposal.domain.proposal.data;

import org.developx.proposal.domain.proposal.entity.Context;
import org.developx.proposal.domain.proposal.pdfbox.ShapeType;

public record Contexts(
        long contextId,
        String context,
        ShapeType shapeType,
        int slideNumber,
        String projectName,
        String documentType
) {
    public static Contexts from(Context context) {

        return new Contexts(
                context.getId(),
                context.getText(),
                context.getShapeType(),
                context.getSlide().getSlideNumber(),
                context.getSlide().getDocument().getProposal().getProject().getProjectName(),
                context.getSlide().getDocument().getProposal().getDocumentType().name()


        );
    }
}
