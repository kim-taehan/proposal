package org.developx.proposal.domain.project.repository.querydsl;

import org.developx.proposal.DataJpaTestSupport;
import org.developx.proposal.domain.customer.data.enums.CustomerType;
import org.developx.proposal.domain.customer.entity.Customer;
import org.developx.proposal.domain.customer.repostory.CustomerRepository;
import org.developx.proposal.domain.project.data.request.FindProjectRequest;
import org.developx.proposal.domain.project.entity.Project;
import org.developx.proposal.domain.project.repository.ProjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.developx.proposal.domain.customer.data.enums.CustomerType.BANK;
import static org.developx.proposal.domain.customer.data.enums.CustomerType.INSURANCE;

@DisplayName("[querydsl] project querydsl repository")
class ProjectRepositoryQueryDslTest extends DataJpaTestSupport {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @DisplayName("프로젝트 조회시 조건 없는 경우 전체가 조회된다.")
    @Test
    void findProjects(){
        // given
        Customer bank01 = createCustomer("테스트 은행사", BANK);
        Customer bank02 = createCustomer("테스트 보험사", INSURANCE);

        Project project01 = createProject("차세대 시스템 프로젝트", "2022", bank01);
        Project project02 = createProject("모바일 창구 시스템", "2023", bank01);
        Project project03 = createProject("EOS 전환 프로젝트", "2023", bank02);

        FindProjectRequest findProjectRequest = new FindProjectRequest("");

        // when
        Page<Project> projects = projectRepository.findProjects(findProjectRequest, Pageable.ofSize(10));

        // then
        assertThat(projects).hasSize(3)
                .contains(project01, project02, project03);
    }

    @DisplayName("프로젝트 조회시 프로젝트 이름으로 조건을 걸수 있다.")
    @Test
    void findProjectsByProjectName(){
        // given
        Customer bank01 = createCustomer("테스트 은행사", BANK);
        Customer bank02 = createCustomer("테스트 보험사", INSURANCE);

        Project project01 = createProject("차세대 시스템 프로젝트", "2022", bank01);
        Project project02 = createProject("모바일 창구 시스템", "2023", bank01);
        Project project03 = createProject("EOS 전환 프로젝트", "2023", bank02);

        FindProjectRequest findProjectRequest = new FindProjectRequest("프로젝트");

        // when
        Page<Project> projects = projectRepository.findProjects(findProjectRequest, Pageable.ofSize(10));

        // then
        assertThat(projects).hasSize(2)
                .contains(project01, project03)
                .doesNotContain(project02);
    }

    private Project createProject(String projectName, String projectYear, Customer customer) {
        return projectRepository.save(
            Project.builder()
                    .projectName(projectName)
                    .projectYear(projectYear)
                    .customer(customer)
                    .build()
        );
    }

    private Customer createCustomer(String customerName, CustomerType customerType) {
        return customerRepository.save(
            Customer.builder()
                .customerType(customerType)
                .customerName(customerName)
                .build()
        );
    }
}