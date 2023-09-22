package org.developx.proposal.domain.proposal.entity;

import jakarta.persistence.*;
import lombok.Builder;
import org.developx.proposal.domain.project.data.enums.DocumentType;
import org.developx.proposal.domain.project.entity.Project;

@Entity
public class Proposal {
    @Id
    @GeneratedValue
    @Column(name = "proposal_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private DocumentType documentType;

    @Builder
    public Proposal(Project project, DocumentType documentType) {
        this.project = project;
        this.documentType = documentType;
    }
}
