package org.developx.proposal.domain.customer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.developx.proposal.domain.customer.data.request.CreateCustomerRequest;
import org.developx.proposal.domain.customer.data.request.FindCustomersRequest;
import org.developx.proposal.domain.customer.data.response.FindCustomersResponse;
import org.developx.proposal.domain.customer.repostory.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Page<FindCustomersResponse> findCustomers(FindCustomersRequest request, Pageable pageable) {
        return customerRepository.findCustomers(request, pageable)
                .map(FindCustomersResponse::from);
    }

    @Transactional
    public void createCustomer(CreateCustomerRequest request) {
        customerRepository.save(request.toEntity());
    }

}
