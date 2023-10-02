package org.developx.proposal.domain.customer.repostory.querydsl;

import org.developx.proposal.domain.customer.data.request.FindCustomersRequest;
import org.developx.proposal.domain.customer.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerRepositoryQueryDsl {

    Page<Customer> findCustomers(FindCustomersRequest request, Pageable pageable);
}
