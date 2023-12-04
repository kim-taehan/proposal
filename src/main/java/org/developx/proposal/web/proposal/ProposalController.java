package org.developx.proposal.web.proposal;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.developx.proposal.domain.project.data.enums.DocumentType;
import org.developx.proposal.domain.project.service.ProjectService;
import org.developx.proposal.domain.proposal.data.ContextDto;
import org.developx.proposal.web.proposal.data.CreateProposalForm;
import org.developx.proposal.web.proposal.data.ProposalsForm;
import org.developx.proposal.domain.proposal.service.ContextServiceImpl;
import org.developx.proposal.domain.proposal.service.ProposalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;

@Controller
@Slf4j
@RequestMapping("/proposals")
@RequiredArgsConstructor
public class ProposalController {

    private final ProjectService projectService;

    private final ProposalService proposalService;

    private final ContextServiceImpl contextService;

    @GetMapping("new")
    public String createForm(Model model) {
        model.addAttribute("createProposalForm", CreateProposalForm.getInstance());
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

    @GetMapping("")
    public String proposals(Model model, ProposalsForm proposalsForm) {
        model.addAttribute("proposalsForm", new ProposalsForm(""));
        model.addAttribute("contexts", contextService.findContexts(proposalsForm.text()));
        return "proposals/proposals";
    }

}
