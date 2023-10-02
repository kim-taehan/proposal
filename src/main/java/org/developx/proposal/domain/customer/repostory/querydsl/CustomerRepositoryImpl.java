package org.developx.proposal.domain.customer.repostory.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.developx.proposal.domain.customer.data.enums.CustomerType;
import org.developx.proposal.domain.customer.data.request.FindCustomersRequest;
import org.developx.proposal.domain.customer.entity.Customer;
import org.developx.proposal.global.infra.Querydsl4RepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import static org.developx.proposal.domain.customer.entity.QCustomer.customer;

public class CustomerRepositoryImpl extends Querydsl4RepositorySupport implements CustomerRepositoryQueryDsl {
    public CustomerRepositoryImpl() {
        super(Customer.class);
    }

    @Override
    public Page<Customer> findCustomers(FindCustomersRequest request, Pageable pageable) {
        return applyPagination(pageable,
                contentQuery -> contentQuery
                        .selectFrom(customer)
                        .where(
                                customerNameLike(request.customerName()),
                                customerTypeEq(request.customerType())
                        )
        );
    }

    private static BooleanExpression customerNameLike(String customerName) {
        return StringUtils.hasText(customerName) ? customer.customerName.like(String.format("%%%s%%", customerName)) : null;
    }

    private static BooleanExpression customerTypeEq(CustomerType customerType) {
        return ObjectUtils.isEmpty(customerType) ? null : customer.customerType.eq(customerType);
    }
}
