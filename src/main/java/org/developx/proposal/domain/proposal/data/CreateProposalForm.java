package org.developx.proposal.domain.proposal.data;

import lombok.Data;
import org.developx.proposal.domain.project.data.enums.DocumentType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CreateProposalForm {
    private DocumentType documentType;
    private long projectId;
    private List<MultipartFile> files;
}
