package org.developx.proposal.domain.user.service;

import org.assertj.core.groups.Tuple;
import org.developx.proposal.SpringBootTestSupport;
import org.developx.proposal.domain.user.data.UsersResponse;
import org.developx.proposal.domain.user.data.request.CreateUserRequest;
import org.developx.proposal.domain.user.data.request.FindUsersRequest;
import org.developx.proposal.domain.user.entity.Team;
import org.developx.proposal.domain.user.entity.User;
import org.developx.proposal.domain.user.repository.TeamRepository;
import org.developx.proposal.domain.user.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("[service] user service")
class UserServiceTest extends SpringBootTestSupport {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;

    @DisplayName("사용자 조회시 조건 없는 경우 전체가 조회된다.")
    @Test
    void findUsers(){
        // given
        Team team1 = createTeamForTest("test team1");
        Team team2 = createTeamForTest("test team2");

        final String password = UUID.randomUUID().toString();
        createTeamForTest("00001", "홍길동", password, team1);
        createTeamForTest("00002", "류관순", password, team1);
        createTeamForTest("00003", "장길산", password, team1);
        createTeamForTest("00004", "임꺽정", password, team2);

        FindUsersRequest request = FindUsersRequest.builder().build();

        // when
        Page<UsersResponse> users = userService.findUsers(request, Pageable.ofSize(10));

        // then
        assertThat(users).hasSize(4)
                .extracting("username", "realName", "team.teamName")
                .containsExactlyInAnyOrder(
                        Tuple.tuple("00001", "홍길동", team1.getTeamName()),
                        Tuple.tuple("00002", "류관순", team1.getTeamName()),
                        Tuple.tuple("00003", "장길산", team1.getTeamName()),
                        Tuple.tuple("00004", "임꺽정", team2.getTeamName())
                );
    }

    @DisplayName("신규 사용자를 등록한다.")
    @Test
    void createUser(){
        // given
        Team team1 = createTeamForTest("test team1");
        final String password = UUID.randomUUID().toString();

        CreateUserRequest request = CreateUserRequest.builder()
                .username("00001")
                .password(password)
                .realName("홍길동")
                .teamId(team1.getId())
                .build();

        // when
        userService.createUser(request);

        // then
        assertThat(userRepository.findAll()).hasSize(1)
                .extracting("username", "password", "realName", "team")
                .contains(Tuple.tuple(request.username(), request.password(), request.realName(), team1));
    }

    @NotNull
    private Team createTeamForTest(String teamName) {
        return teamRepository.save(
                Team.builder()
                        .teamName(teamName)
                        .build()
        );
    }
    @DisplayName("신규 사용자를 등록시에 Team 정보가 잘못된 경우 애러가 발생한다.")
    @Test
    void createUserNoTeamId(){
        // given
        final String password = UUID.randomUUID().toString();

        CreateUserRequest request = CreateUserRequest.builder()
                .username("00001")
                .password(password)
                .realName("홍길동")
                .teamId(1)
                .build();

        // when
        // then
        assertThatThrownBy(() -> userService.createUser(request))
                .isInstanceOf(IllegalArgumentException.class);

    }

    private void createTeamForTest(String username, String realName, String password, Team team) {
        userRepository.save(
                User.builder()
                        .username(username)
                        .realName(realName)
                        .password(password)
                        .team(team)
                        .build()
        );
    }


}