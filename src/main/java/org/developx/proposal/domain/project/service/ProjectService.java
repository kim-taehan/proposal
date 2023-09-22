package org.developx.proposal.domain.project.service;

import lombok.RequiredArgsConstructor;
import org.developx.proposal.domain.project.data.ProjectForm;
import org.developx.proposal.domain.project.data.ProjectsResponse;
import org.developx.proposal.domain.project.entity.Project;
import org.developx.proposal.domain.project.repository.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Optional<Project> findById(Long projectId) {
        return projectRepository.findById(projectId);
    }

    public List<ProjectsResponse> findAll() {
        return projectRepository.findAll().stream().map(ProjectsResponse::from).toList();
    }

    public Page<ProjectsResponse> findProjects() {
        return projectRepository.findProjects(Pageable.ofSize(10))
                .map(ProjectsResponse::from);
    }

    public void createProject(ProjectForm projectForm) {
        Project project = new Project(projectForm.getProjectName(), projectForm.getCustomer());
        projectRepository.save(project);
    }
}
