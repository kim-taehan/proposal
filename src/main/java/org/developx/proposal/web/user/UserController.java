package org.developx.proposal.web.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.developx.proposal.domain.user.data.response.FindUserResponse;
import org.developx.proposal.domain.user.data.response.UsersResponse;
import org.developx.proposal.web.user.data.CreateUserForm;
import org.developx.proposal.domain.user.service.TeamService;
import org.developx.proposal.domain.user.service.UserService;
import org.developx.proposal.web.user.data.UsersForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TeamService teamService;

    @GetMapping("/users")
    public String users(@Valid UsersForm usersForm, Model model){
        Page<UsersResponse> users = userService.findUsers(
                usersForm.toFindUserRequest(),
                Pageable.ofSize(10)
        );
        model.addAttribute("users", users);
        return "users/users";
    }

    @GetMapping("/users/new")
    public String createForm(Model model) {
        model.addAttribute("createUserForm", CreateUserForm.getInstance());
        model.addAttribute("teams", teamService.findAllTeam());
        return "users/createUserForm";
    }

    @PostMapping("/users/new")
    public String create(@Valid CreateUserForm createUserForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("teams", teamService.findAllTeam());
            return "users/createUserForm";
        }
        userService.createUser(createUserForm.toCreateUserRequest());
        return "redirect:/";
    }

    @GetMapping("/users/{userId}")
    public String findUser(@PathVariable("userId") Long userId, Model model){
        FindUserResponse findUserResponse = userService.findUser(userId);
        model.addAttribute("user", findUserResponse);
        return "users/user";
    }
}
