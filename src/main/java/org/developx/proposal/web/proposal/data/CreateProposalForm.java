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
    public CreateProposalForm(DocumentType documentType, long projectId, List<MultipartFile> files) {
        this.documentType = documentType;
        this.projectId = projectId;
        this.files = files;
    }

    public static CreateProposalForm getInstance(){
        return new CreateProposalForm(PROPOSAL, 0L, Collections.emptyList());
    }
}
