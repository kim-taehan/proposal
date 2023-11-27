package org.developx.proposal.web.proposal.data;

import org.developx.proposal.domain.project.data.enums.DocumentType;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

import static org.developx.proposal.domain.project.data.enums.DocumentType.*;

public record CreateProposalForm (
        DocumentType documentType,
        long projectId,
        List<MultipartFile> files
) {

    public static CreateProposalForm empty(){
        return new CreateProposalForm(PROPOSAL, 0L, Collections.emptyList());
    }
}
