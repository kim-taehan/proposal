package org.developx.proposal.domain.proposal.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.developx.proposal.domain.proposal.entity.embed.FileInfo;

import java.nio.file.Path;
import java.nio.file.Paths;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Document {

    @Id
    @GeneratedValue
    @Column(name = "document_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposal_id")
    private Proposal proposal;

    @Embedded
    private FileInfo fileInfo;

    private int totalSlide;

    @Builder
    public Document(Proposal proposal, FileInfo fileInfo) {
        this.proposal = proposal;
        this.fileInfo = fileInfo;
    }

    public Path getPath() {
        return Paths.get(fileInfo.getSaveFilePath(), fileInfo.getSaveFileName());
    }

    public void setTotalSlide(int totalSlide) {
        this.totalSlide = totalSlide;
    }
}
