package org.developx.proposal.domain.customer.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.developx.proposal.domain.customer.data.enums.CustomerType;
import org.developx.proposal.global.infra.jpa.BaseEntity;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    @Column(unique=true, length = 50)
    private String customerName;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private CustomerType customerType;

    @Builder
    public Customer(String customerName, CustomerType customerType) {
        this.customerName = customerName;
        this.customerType = customerType;
    }
}
