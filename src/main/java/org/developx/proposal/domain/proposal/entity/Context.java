package org.developx.proposal.domain.proposal.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.developx.proposal.domain.proposal.pdfbox.ShapeType;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Context {

    @Id
    @GeneratedValue
    @Column(name = "context_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slide_id")
    private Slide slide;

    @Column(length = 255)
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private ShapeType shapeType;


    @Builder
    public Context(Slide slide, String text, ShapeType shapeType) {
        this.slide = slide;
        this.text = text;
        this.shapeType = shapeType;
    }
}
