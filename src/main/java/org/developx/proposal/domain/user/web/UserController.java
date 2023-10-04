package org.developx.proposal.domain.user.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.developx.proposal.domain.user.data.UserForm;
import org.developx.proposal.domain.user.service.TeamService;
import org.developx.proposal.domain.user.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TeamService teamService;

    @GetMapping("/users")
    public String users(Model model){
        model.addAttribute("users", userService.findUsers(null, Pageable.ofSize(10)));
        return "users/users";
    }

    @GetMapping("/users/new")
    public String createForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        model.addAttribute("teams", teamService.findAllTeam());
        return "users/createUserForm";
    }

    @PostMapping("/users/new")
    public String create(@Valid UserForm userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("teams", teamService.findAllTeam());
            return "users/createUserForm";
        }
        userService.createUser(userForm.toCreateUserRequest());
        return "redirect:/";
    }
}
