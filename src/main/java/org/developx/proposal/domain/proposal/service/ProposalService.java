package org.developx.proposal.domain.proposal.service;

import lombok.RequiredArgsConstructor;
import org.developx.proposal.domain.project.entity.Project;
import org.developx.proposal.domain.project.service.ProjectService;
import org.developx.proposal.domain.proposal.data.CreateProposalForm;
import org.developx.proposal.domain.proposal.entity.Proposal;
import org.developx.proposal.domain.proposal.entity.embed.FileInfo;
import org.developx.proposal.domain.proposal.repository.ProposalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProposalService {

    private final ProposalRepository proposalRepository;
    private final ProjectService projectService;
    private final DocumentService documentService;

    @Transactional
    public void createProposal(CreateProposalForm form) {

        Project project = projectService.findById(form.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException());
        Proposal proposal = Proposal.builder()
                .documentType(form.getDocumentType())
                .project(project)
                .build();
        proposalRepository.save(proposal);
        for (MultipartFile file : form.getFiles()) {
            documentService.saveDocument(proposal, file);
        }

    }


}
