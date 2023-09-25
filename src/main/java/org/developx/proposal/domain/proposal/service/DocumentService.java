package org.developx.proposal.domain.proposal.service;

import lombok.RequiredArgsConstructor;
import org.developx.proposal.domain.proposal.entity.Document;
import org.developx.proposal.domain.proposal.entity.Proposal;
import org.developx.proposal.domain.proposal.entity.embed.FileInfo;
import org.developx.proposal.domain.proposal.repository.DocumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final SlideService slideService;

    public void saveDocument(Proposal proposal, MultipartFile file) {
        Document document = documentRepository.save(
                Document.builder()
                        .proposal(proposal)
                        .fileInfo(FileInfo.from(file))
                        .build()
        );
        slideService.saveSlides(document);
    }
}
