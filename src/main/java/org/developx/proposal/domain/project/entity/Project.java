package org.developx.proposal.domain.project.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.developx.proposal.domain.customer.entity.Customer;
import org.developx.proposal.global.infra.jpa.BaseEntity;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Project extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "project_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String projectName;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(length = 4)
    private String projectYear;

    @Builder
    public Project(String projectName, Customer customer, String projectYear) {
        this.projectName = projectName;
        this.customer = customer;
        this.projectYear = projectYear;
    }
}
