package org.developx.proposal.domain.project.data.response;

import org.developx.proposal.SpringBootTestSupport;
import org.developx.proposal.domain.customer.data.enums.CustomerType;
import org.developx.proposal.domain.customer.entity.Customer;
import org.developx.proposal.domain.customer.repostory.CustomerRepository;
import org.developx.proposal.domain.project.entity.Project;
import org.developx.proposal.domain.project.repository.ProjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[record] FindProjectsResponse")
class FindProjectsResponseTest extends SpringBootTestSupport {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProjectRepository projectRepository;
    @DisplayName("project entity를 화면에서 원하는 형태로 변환한다.")
    @Test
    void projectToDisplayDto(){

        // given
        Customer customer = Customer.builder()
                .customerName("테스트 고객사")
                .customerType(CustomerType.BANK)
                .build();
        customerRepository.save(customer);

        // given
        Project project = Project.builder()
                .projectName("테스트 프로젝트")
                .projectYear("2023")
                .customer(customer)
                .build();
        projectRepository.save(project);

        // when
        FindProjectsResponse findProjectsResponse = FindProjectsResponse.from(project);

        // then
        assertThat(findProjectsResponse).isNotNull()
                .extracting("projectName", "projectYear", "customer.customerName", "customer.customerType")
                .contains("테스트 프로젝트", "2023", "테스트 고객사", CustomerType.BANK);

        assertThat(findProjectsResponse.projectId()).isPositive();
    }
}