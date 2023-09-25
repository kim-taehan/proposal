package org.developx.proposal.domain.proposal.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Slide {

    @Id
    @GeneratedValue
    @Column(name = "slide_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    private Document document;

    private int slideNumber;

    @Builder
    public Slide(Document document, int slideNumber) {
        this.document = document;
        this.slideNumber = slideNumber;
    }
}
