package org.developx.proposal.domain.project.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.developx.proposal.global.infra.jpa.BaseEntity;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Project extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "project_id")
    private Long id;

    @Column(length = 50)
    private String projectName;

    @Column(length = 50)
    private String customer;

    @Builder
    public Project(String projectName, String customer) {
        this.projectName = projectName;
        this.customer = customer;
    }
}
