package org.developx.proposal.web.project;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.developx.proposal.domain.customer.data.enums.CustomerType;
import org.developx.proposal.domain.customer.service.CustomerService;
import org.developx.proposal.domain.project.data.request.CreateProjectRequest;
import org.developx.proposal.domain.project.data.response.FindProjectsResponse;
import org.developx.proposal.domain.project.service.ProjectService;
import org.developx.proposal.web.project.data.CreateProjectForm;
import org.developx.proposal.web.project.data.ProjectsForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final CustomerService customerService;

    @GetMapping("/projects")
    public String users(Model model, ProjectsForm projectsForm){
        Page<FindProjectsResponse> projects = projectService.findProjects(
                projectsForm.toFindProjectRequest(),
                Pageable.ofSize(10)
        );

        model.addAttribute("projects", projects);
        return "projects/projects";
    }

    @GetMapping("/projects/new")
    public String createForm(Model model) {
        model.addAttribute("createProjectForm", CreateProjectForm.getInstance());
        model.addAttribute("customerTypes", CustomerType.values());
        model.addAttribute("customers", customerService.findAll());
        return "projects/createProjectForm";
    }

    @PostMapping("/projects/new")
    public String create(@Valid CreateProjectForm createProjectForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("customerTypes", CustomerType.values());
            model.addAttribute("customers", customerService.findAll());
            return "projects/createProjectForm";
        }
        CreateProjectRequest createProjectRequest = createProjectForm.toCreateProjectRequest();
        projectService.createProject(createProjectRequest);
        return "redirect:/projects";
    }
}
