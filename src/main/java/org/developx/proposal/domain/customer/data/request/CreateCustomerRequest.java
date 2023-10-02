package org.developx.proposal.domain.customer.data.request;

import org.developx.proposal.domain.customer.data.enums.CustomerType;
import org.developx.proposal.domain.customer.entity.Customer;

public record CreateCustomerRequest(
        String customerName,
        CustomerType customerType
) {
    public Customer toEntity() {
        return Customer.builder()
                .customerName(customerName)
                .customerType(customerType)
                .build();
    }
}
