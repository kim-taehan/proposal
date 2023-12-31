package org.developx.proposal.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.developx.proposal.domain.user.data.response.FindUserResponse;
import org.developx.proposal.domain.user.data.response.UsersResponse;
import org.developx.proposal.domain.user.data.request.CreateUserRequest;
import org.developx.proposal.domain.user.data.request.FindUsersRequest;
import org.developx.proposal.domain.user.entity.Team;
import org.developx.proposal.domain.user.entity.User;
import org.developx.proposal.domain.user.repository.UserRepository;
import org.developx.proposal.global.infra.jpa.SpringSecurityAuditorAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final TeamService teamService;
    private final PasswordEncoder passwordEncoder;

    private final SpringSecurityAuditorAware auditorAware;

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public Page<UsersResponse> findUsers(FindUsersRequest request, Pageable pageable) {
        Page<User> users = userRepository.findUsers(request, pageable);
        return users.map(UsersResponse::from);
    }

    @Transactional
    public void createUser(CreateUserRequest request) {
        Team team = teamService.findById(request.teamId());
        User user = request.toEntity(team, passwordEncoder);
        userRepository.save(user);
    }

    public FindUserResponse findUser(Long userId) {
        return FindUserResponse.from(userRepository.findByUser(userId));
    }
    
    
    public User findLoginUser() {
        String username = auditorAware.getCurrentAuditor().orElseThrow(() -> new IllegalStateException("로그인정보를 확인할 수 없습니다."));
        return findByUsername(username);
    }

}
