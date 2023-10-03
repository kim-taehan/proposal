package org.developx.proposal.domain.project.service;

import org.assertj.core.groups.Tuple;
import org.developx.proposal.SpringBootTestSupport;
import org.developx.proposal.domain.customer.entity.Customer;
import org.developx.proposal.domain.customer.repostory.CustomerRepository;
import org.developx.proposal.domain.project.data.request.CreateProjectRequest;
import org.developx.proposal.domain.project.data.request.FindProjectRequest;
import org.developx.proposal.domain.project.data.response.FindProjectsResponse;
import org.developx.proposal.domain.project.entity.Project;
import org.developx.proposal.domain.project.repository.ProjectRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.developx.proposal.domain.customer.data.enums.CustomerType.BANK;

@DisplayName("[service] project service")
class ProjectServiceTest extends SpringBootTestSupport {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    @DisplayName("프로젝트 조회시에 조건이 없는 경우 전체가 조회된다.")
    @Test
    void findProjects(){
        // given
        Customer customer = customerRepository.save(
                Customer.builder()
                    .customerName("테스트고객사")
                    .customerType(BANK)
                    .build()
        );

        createProjectForTest("차세대 시스템 프로젝트", "2022", customer);
        createProjectForTest("모바일 창구 시스템", "2023", customer);
        createProjectForTest("EOS 전환 프로젝트", "2023", customer);
        FindProjectRequest findProjectRequest = new FindProjectRequest("");

        // when
        Page<FindProjectsResponse> projects = projectService.findProjects(findProjectRequest, Pageable.ofSize(10));

        // then
        assertThat(projects).hasSize(3)
                .extracting("projectName", "projectYear", "customer.customerName", "customer.customerType")
                .containsExactlyInAnyOrder(
                        Tuple.tuple("차세대 시스템 프로젝트", "2022", customer.getCustomerName(), customer.getCustomerType()),
                        Tuple.tuple("모바일 창구 시스템", "2023", customer.getCustomerName(), customer.getCustomerType()),
                        Tuple.tuple("EOS 전환 프로젝트", "2023", customer.getCustomerName(), customer.getCustomerType())
                );
    }


    @DisplayName("신규 프로젝트를 등록한다.")
    @Test
    void createProject(){
        // given
        Customer customer = customerRepository.save(
                Customer.builder()
                        .customerName("테스트고객사")
                        .customerType(BANK)
                        .build()
        );

        CreateProjectRequest request = CreateProjectRequest.builder()
                .projectYear("2023")
                .projectName("차세대 프로젝트")
                .customerId(customer.getId())
                .build();

        // when
        projectService.createProject(request);

        // then
        assertThat(projectService.findAll()).hasSize(1)
                .extracting("projectName", "projectYear", "customer.customerName", "customer.customerType")
                .contains(Tuple.tuple(request.projectName(), request.projectYear(), customer.getCustomerName(), customer.getCustomerType()));
    }

    @DisplayName("신규 프로젝트를 등록시에 프로젝트 이름이 없는 경우 애러가 발생한다.")
    @Test
    void createProjectNoProjectName(){
        // given
        Customer customer = customerRepository.save(
                Customer.builder()
                        .customerName("테스트고객사")
                        .customerType(BANK)
                        .build()
        );

        CreateProjectRequest request = CreateProjectRequest.builder()
                .projectYear("2023")
                .customerId(customer.getId())
                .build();

        // when
        projectService.createProject(request);

        // then
        assertThatThrownBy(() -> entityManager.flush())
                .isInstanceOf(ConstraintViolationException.class);
    }

    private void createProjectForTest(String projectName, String projectYear, Customer customer) {

        Project project = Project.builder()
                .projectName(projectName)
                .projectYear(projectYear)
                .customer(customer)
                .build();

        projectRepository.save(project);
    }

}