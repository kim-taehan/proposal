package org.developx.proposal.domain.project.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.developx.proposal.domain.project.data.ProjectForm;
import org.developx.proposal.domain.project.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/projects")
    public String users(Model model){
        model.addAttribute("projects", projectService.findProjects());
        return "projects/projects";
    }

    @GetMapping("/projects/new")
    public String createForm(Model model) {
        model.addAttribute("projectForm", new ProjectForm());
        return "projects/createProjectForm";
    }

    @PostMapping("/projects/new")
    public String create(@Valid ProjectForm projectForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "projects/createProjectForm";
        }
        projectService.createProject(projectForm);
        return "redirect:/";
    }
}
