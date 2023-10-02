package org.developx.proposal.domain.customer.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.groups.Tuple;
import org.developx.proposal.domain.customer.data.enums.CustomerType;
import org.developx.proposal.domain.customer.data.request.CreateCustomerRequest;
import org.developx.proposal.domain.customer.data.request.FindCustomersRequest;
import org.developx.proposal.domain.customer.data.response.FindCustomersResponse;
import org.developx.proposal.domain.customer.entity.Customer;
import org.developx.proposal.domain.customer.repostory.CustomerRepository;
import org.developx.proposal.SpringBootTestSupport;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.developx.proposal.domain.customer.data.enums.CustomerType.BANK;
import static org.developx.proposal.domain.customer.data.enums.CustomerType.INSURANCE;

@Slf4j
@DisplayName("[service] customer service")
class CustomerServiceTest extends SpringBootTestSupport {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @DisplayName("고객사 조회시 조건에 따라 고객사가 정상적으로 조회 됩니다.")
    @Test
    void findCustomers(){
        // given
        createCustomer("은행고객사1", BANK);
        createCustomer("은행고객사2", BANK);
        createCustomer("생명보험고객사", INSURANCE);

        FindCustomersRequest findCustomersRequest = new FindCustomersRequest("은행", BANK);

        // when
        Page<FindCustomersResponse> customers = customerService.findCustomers(findCustomersRequest, Pageable.ofSize(10));

        // then
        assertThat(customers).hasSize(2)
                .extracting("customerName", "customerType")
                .containsExactlyInAnyOrder(
                        Tuple.tuple("은행고객사1", BANK),
                        Tuple.tuple("은행고객사2", BANK)
                );
    }

    @DisplayName("고객사 조회시 조건에 충족되는 데이터가 없어도 애러가 발생하지 않는다.")
    @Test
    void findCustomersIsNull(){
        // given
        createCustomer("은행고객사1", BANK);
        createCustomer("은행고객사2", BANK);
        createCustomer("생명보험고객사", INSURANCE);

        FindCustomersRequest findCustomersRequest = new FindCustomersRequest("X 데이터", BANK);

        // when
        Page<FindCustomersResponse> customers = customerService.findCustomers(findCustomersRequest, Pageable.ofSize(10));

        // then
        assertThat(customers).hasSize(0);
    }

    @DisplayName("신규로 고객사를 등록한다.")
    @Test
    void createCustomer(){
        // give
        CreateCustomerRequest 테스트_고객사 = new CreateCustomerRequest("테스트 고객사", CustomerType.STOCK);

        // when & then
        customerService.createCustomer(테스트_고객사);
    }

    @DisplayName("신규로 고객사를 등록시에 동일한 고객사명은 사용할 수 없다.")
    @Test
    void createCustomerDuplicatedName(){
        // give
        createCustomer("테스트 고객사", BANK);
        CreateCustomerRequest 테스트_고객사 = new CreateCustomerRequest("테스트 고객사", CustomerType.STOCK);

        // when
        customerService.createCustomer(테스트_고객사);

        // then
        assertThatThrownBy(() -> entityManager.flush())
                .isInstanceOf(ConstraintViolationException.class);
    }

    private void createCustomer(String customerName, CustomerType customerType) {
        Customer customer = Customer.builder()
                .customerName(customerName)
                .customerType(customerType)
                .build();
        customerRepository.save(customer);
    }
}