package org.developx.proposal.web.customer.data;

import org.developx.proposal.domain.customer.data.enums.CustomerType;
import org.developx.proposal.domain.customer.data.request.FindCustomersRequest;

public record CustomersForm(
        String customerName,
        CustomerType customerType
) {

    public static CustomersForm getInstance() {
        return new CustomersForm("", CustomerType.BANK);
    }
    public FindCustomersRequest toFindCustomerRequest() {
        return new FindCustomersRequest(customerName, customerType);
    }
}
