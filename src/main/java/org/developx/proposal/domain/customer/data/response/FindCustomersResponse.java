package org.developx.proposal.domain.customer.data.response;

import org.developx.proposal.domain.customer.data.enums.CustomerType;
import org.developx.proposal.domain.customer.entity.Customer;

public record FindCustomersResponse (long customerId, String customerName, CustomerType customerType)
{
    public static FindCustomersResponse from(Customer customer) {
        return new FindCustomersResponse(
                customer.getId(),
                customer.getCustomerName(),
                customer.getCustomerType()
        );
    }
}
