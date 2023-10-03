package org.developx.proposal.domain.project.service;

import lombok.RequiredArgsConstructor;
import org.developx.proposal.domain.customer.entity.Customer;
import org.developx.proposal.domain.customer.service.CustomerService;
import org.developx.proposal.domain.project.data.request.CreateProjectRequest;
import org.developx.proposal.web.project.data.CreateProjectForm;
import org.developx.proposal.domain.project.data.request.FindProjectRequest;
import org.developx.proposal.domain.project.data.response.FindProjectsResponse;
import org.developx.proposal.domain.project.entity.Project;
import org.developx.proposal.domain.project.repository.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final CustomerService customerService;

    public Optional<Project> findById(Long projectId) {
        return projectRepository.findById(projectId);
    }

    public List<FindProjectsResponse> findAll() {
        return projectRepository.findAll().stream()
                .map(FindProjectsResponse::from)
                .toList();
    }

    public Page<FindProjectsResponse> findProjects(FindProjectRequest request, Pageable pageable) {
        return projectRepository.findProjects(request, pageable)
                .map(FindProjectsResponse::from);
    }

    @Transactional
    public void createProject(CreateProjectRequest request) {
        Customer customer = customerService.findById(request.customerId());
        Project saveProject = Project.builder()
                .projectName(request.projectName())
                .projectYear(request.projectYear())
                .customer(customer)
                .build();
        projectRepository.save(saveProject);
    }
}
