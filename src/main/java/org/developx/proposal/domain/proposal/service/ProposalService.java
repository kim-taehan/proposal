package org.developx.proposal.domain.proposal.service;

import lombok.RequiredArgsConstructor;
import org.developx.proposal.domain.project.entity.Project;
import org.developx.proposal.domain.project.service.ProjectService;
import org.developx.proposal.domain.proposal.data.CreateProposalForm;
import org.developx.proposal.domain.proposal.entity.Proposal;
import org.developx.proposal.domain.proposal.repository.ProposalRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class ProposalService {

    private final ProposalRepository proposalRepository;
    private final ProjectService projectService;
    public void createProposal(CreateProposalForm form) {

        Project project = projectService.findById(form.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException());

        for (MultipartFile file : form.getFiles()) {


        }


        Proposal proposal = Proposal.builder()
                .documentType(form.getDocumentType())
                .project(project)
                .build();
        proposalRepository.save(proposal);
    }
}
