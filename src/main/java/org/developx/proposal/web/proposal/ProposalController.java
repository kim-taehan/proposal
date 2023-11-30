package org.developx.proposal.web.proposal;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.developx.proposal.domain.project.data.enums.DocumentType;
import org.developx.proposal.domain.project.service.ProjectService;
import org.developx.proposal.domain.proposal.data.ContextDto;
import org.developx.proposal.web.proposal.data.CreateProposalForm;
import org.developx.proposal.web.proposal.data.ProposalsForm;
import org.developx.proposal.domain.proposal.service.ContextService;
import org.developx.proposal.domain.proposal.service.ProposalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;

@Controller
@RequestMapping("/proposals")
@RequiredArgsConstructor
public class ProposalController {

    private final ProjectService projectService;

    private final ProposalService proposalService;

    private final ContextService contextService;

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

    @GetMapping("{contextId}")
    public String detail(@PathVariable("contextId") Long contextId, Model model) {
        ContextDto contextDto = contextService.findContext(contextId);
        model.addAttribute("pdfUrl", "/proposals/download/"+contextId+"#page="+(contextDto.slideNumber()-1) + "#toolbar=0&navpanes=0&scrollbar=0");

        return "proposals/preview";
    }


    @GetMapping("download/{contextId}")
    public StreamingResponseBody downloadReport(@PathVariable("contextId") Long contextId, HttpServletResponse response) throws FileNotFoundException {

        response.setContentType("application/pdf");
        ContextDto contextDto = contextService.findContext(contextId);
        File targetFile = contextDto.pullPath().toFile();
        InputStream targetStream =  new DataInputStream(new FileInputStream(targetFile));

        return outputStream -> {
            int nRead;
            byte[] data = new byte[4069];
            while ((nRead = targetStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, nRead);
            }
        };
    }


}
