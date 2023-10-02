package org.developx.proposal.domain.customer.data.request;

import org.developx.proposal.domain.customer.data.enums.CustomerType;

public record FindCustomersRequest(
        String customerName,
        CustomerType customerType
) {
}
