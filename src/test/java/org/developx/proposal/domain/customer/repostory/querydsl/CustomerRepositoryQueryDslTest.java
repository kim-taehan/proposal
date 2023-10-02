package org.developx.proposal.domain.customer.repostory.querydsl;

import lombok.RequiredArgsConstructor;
import org.assertj.core.groups.Tuple;
import org.developx.proposal.domain.customer.data.enums.CustomerType;
import org.developx.proposal.domain.customer.data.request.FindCustomersRequest;
import org.developx.proposal.domain.customer.entity.Customer;
import org.developx.proposal.domain.customer.repostory.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.developx.proposal.domain.customer.data.enums.CustomerType.*;

@ActiveProfiles("test")
@RequiredArgsConstructor
@Transactional
@DataJpaTest
@DisplayName("[querydsl] CustomerRepositoryQueryDsl")
class CustomerRepositoryQueryDslTest {

    @Autowired
    private CustomerRepository customerRepository;

    @DisplayName("고객사 조회시에 조건이 없는 경우 전체가 검색된다.")
    @Test
    void findCustomersNoCondition() {

        // given
        createCustomer("은행고객사1", BANK);
        createCustomer("은행고객사2", BANK);
        createCustomer("생명보험고객사", INSURANCE);
        createCustomer("손해보험고객사", INSURANCE);
        createCustomer("증권고객사", STOCK);
        createCustomer("보험은행", INSURANCE);

        FindCustomersRequest findCustomersRequest = new FindCustomersRequest("", null);

        // when
        Page<Customer> customers = customerRepository.findCustomers(findCustomersRequest, Pageable.ofSize(10));

        // then
        assertThat(customers).hasSize(6)
                .extracting("customerName", "customerType")
                .containsExactlyInAnyOrder(
                        Tuple.tuple("은행고객사1", BANK),
                        Tuple.tuple("은행고객사2", BANK),
                        Tuple.tuple("생명보험고객사", INSURANCE),
                        Tuple.tuple("손해보험고객사", INSURANCE),
                        Tuple.tuple("증권고객사", STOCK),
                        Tuple.tuple("보험은행", INSURANCE)
                );
    }


    @DisplayName("고객사 조회시에 고객유형이 은행인 고객사만 검색할 수 있다.")
    @Test
    void findCustomersOnlyBank() {

        // given
        createCustomer("은행고객사1", BANK);
        createCustomer("은행고객사2", BANK);
        createCustomer("생명보험고객사", INSURANCE);
        createCustomer("손해보험고객사", INSURANCE);
        createCustomer("증권고객사", STOCK);
        createCustomer("보험은행", INSURANCE);

        FindCustomersRequest findCustomersRequest = new FindCustomersRequest("", BANK);

        // when
        Page<Customer> customers = customerRepository.findCustomers(findCustomersRequest, Pageable.ofSize(10));

        // then
        assertThat(customers).hasSize(2)
                .extracting("customerName", "customerType")
                .containsExactlyInAnyOrder(
                        Tuple.tuple("은행고객사1", BANK),
                        Tuple.tuple("은행고객사2", BANK)
                );
    }

    @DisplayName("고객사 조회시에 고객사명에 '은행'이 들어간 고객사만 검색할 수 있다.")
    @Test
    void findCustomersLikeSearch() {

        // given
        createCustomer("은행고객사1", BANK);
        createCustomer("은행고객사2", BANK);
        createCustomer("생명보험고객사", INSURANCE);
        createCustomer("손해보험고객사", INSURANCE);
        createCustomer("증권고객사", STOCK);
        createCustomer("보험은행", INSURANCE);

        FindCustomersRequest findCustomersRequest = new FindCustomersRequest("은행", null);

        // when
        Page<Customer> customers = customerRepository.findCustomers(findCustomersRequest, Pageable.ofSize(10));

        // then
        assertThat(customers).hasSize(3)
                .extracting("customerName", "customerType")
                .containsExactlyInAnyOrder(
                        Tuple.tuple("은행고객사1", BANK),
                        Tuple.tuple("은행고객사2", BANK),
                        Tuple.tuple("보험은행", INSURANCE)
                );
    }

    private void createCustomer(String customerName, CustomerType customerType) {
        Customer customer = Customer.builder()
                .customerName(customerName)
                .customerType(customerType)
                .build();
        customerRepository.save(customer);
    }
}