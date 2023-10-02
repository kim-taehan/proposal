package org.developx.proposal.web.customer.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.developx.proposal.domain.customer.data.enums.CustomerType;
import org.developx.proposal.domain.customer.data.request.CreateCustomerRequest;

public record CreateCustomerForm(
        @NotBlank
        String customerName,
        @NotNull
        CustomerType customerType
) {
        public static CreateCustomerForm getInstance() {
                return new CreateCustomerForm("", CustomerType.BANK);
        }

        public CreateCustomerRequest toCreateCustomerRequest() {
                return new CreateCustomerRequest(customerName, customerType);
        }
}
