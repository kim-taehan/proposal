package org.developx.proposal.domain.proposal.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.developx.proposal.domain.project.data.enums.DocumentType;
import org.developx.proposal.domain.project.service.ProjectService;
import org.developx.proposal.domain.proposal.data.CreateProposalForm;
import org.developx.proposal.domain.proposal.service.ProposalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proposals")
@RequiredArgsConstructor
public class ProposalController {

    private final ProjectService projectService;

    private final ProposalService proposalService;

    @GetMapping("new")
    public String createForm(Model model) {
        model.addAttribute("createProposalForm", new CreateProposalForm());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("documentTypes", DocumentType.values());
        return "proposals/createProposalForm";
    }

    @PostMapping("new")
    public String create(@Valid @ModelAttribute CreateProposalForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("projects", projectService.findAll());
            model.addAttribute("documentTypes", DocumentType.values());
            return "proposals/createProposalForm";
        }

        proposalService.createProposal(form);
        return "redirect:/";
    }
}
