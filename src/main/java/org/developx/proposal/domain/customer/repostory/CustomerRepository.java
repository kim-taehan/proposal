package org.developx.proposal.domain.customer.repostory;

import org.developx.proposal.domain.customer.entity.Customer;
import org.developx.proposal.domain.customer.repostory.querydsl.CustomerRepositoryQueryDsl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryQueryDsl {
}
