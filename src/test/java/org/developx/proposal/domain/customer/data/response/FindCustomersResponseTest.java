package org.developx.proposal.domain.customer.data.response;

import org.developx.proposal.domain.customer.data.enums.CustomerType;
import org.developx.proposal.domain.customer.entity.Customer;
import org.developx.proposal.domain.customer.repostory.CustomerRepository;
import org.developx.proposal.SpringBootTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[record] FindCustomerResponse")
class FindCustomersResponseTest extends SpringBootTestSupport {

    @Autowired
    private CustomerRepository customerRepository;

    @DisplayName("고객사 조회시에 Customer entity를 클라이언트가 원하는 응답결과로 변경한다.")
    @Test
    void from(){
        // given
        Customer customer = Customer.builder()
                .customerName("테스트 고객사")
                .customerType(CustomerType.BANK)
                .build();

        customerRepository.save(customer);

        // when
        FindCustomersResponse findCustomersResponse = FindCustomersResponse.from(customer);

        // then
        assertThat(findCustomersResponse)
                .isNotNull()
                .extracting("customerName", "customerType")
                .contains("테스트 고객사", CustomerType.BANK);
        assertThat(findCustomersResponse.customerId()).isNotNull();
    }

}