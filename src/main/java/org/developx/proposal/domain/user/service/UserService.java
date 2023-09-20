package org.developx.proposal.domain.user.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.developx.proposal.domain.user.data.UserForm;
import org.developx.proposal.domain.user.data.UsersResponse;
import org.developx.proposal.domain.user.entity.Team;
import org.developx.proposal.domain.user.entity.User;
import org.developx.proposal.domain.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final TeamService teamService;


    public Page<UsersResponse> findUsers() {
        Page<User> users = userRepository.findUsers(Pageable.ofSize(10));
        return users.map(UsersResponse::from);
    }

    @Transactional
    public void createUser(@Valid UserForm userForm) {

        Team team = teamService.findById(userForm.getTeamId())
                .orElseThrow(IllegalArgumentException::new);
        User saveUser = User.builder()
                .username(userForm.getUsername())
                .realName(userForm.getRealName())
                .password(userForm.getPassword())
                .team(team)
                .build();
        userRepository.save(saveUser);
    }
}
