package org.developx.proposal.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.developx.proposal.domain.user.data.UserForm;
import org.developx.proposal.domain.user.data.UsersResponse;
import org.developx.proposal.domain.user.data.request.CreateUserRequest;
import org.developx.proposal.domain.user.data.request.FindUsersRequest;
import org.developx.proposal.domain.user.entity.Team;
import org.developx.proposal.domain.user.entity.User;
import org.developx.proposal.domain.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final TeamService teamService;

    public Page<UsersResponse> findUsers(FindUsersRequest request, Pageable pageable) {
        Page<User> users = userRepository.findUsers(request, pageable);
        return users.map(UsersResponse::from);
    }

    @Transactional
    public void createUser(CreateUserRequest request) {
        Team team = teamService.findById(request.teamId());
        User user = request.toEntity(team);
        userRepository.save(user);
    }
}
