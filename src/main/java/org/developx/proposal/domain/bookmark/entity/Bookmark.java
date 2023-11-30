package org.developx.proposal.domain.bookmark.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.developx.proposal.domain.customer.entity.Customer;
import org.developx.proposal.domain.proposal.entity.Slide;
import org.developx.proposal.domain.user.entity.User;
import org.developx.proposal.global.infra.jpa.BaseEntity;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Bookmark extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "bookmark_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slide_id")
    private Slide slide;

    @Column(length = 100)
    private String comment;

    @Builder
    public Bookmark(User user, Slide slide, String comment) {
        this.user = user;
        this.slide = slide;
        this.comment = comment;
    }
}
