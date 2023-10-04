package org.developx.proposal.domain.user.repository.querydsl;

import org.developx.proposal.DataJpaTestSupport;
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

@DisplayName("[querydsl] users querydsl repository")
class UserRepositoryImplTest extends DataJpaTestSupport {

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
        User user01 = createTeamForTest("00001", "홍길동", password, team1);
        User user02 = createTeamForTest("00002", "류관순", password, team1);
        User user03 = createTeamForTest("00003", "장길산", password, team1);
        User user04 = createTeamForTest("00004", "임꺽정", password, team2);

        FindUsersRequest request = FindUsersRequest.builder().build();

        // when
        Page<User> users = userRepository.findUsers(request, Pageable.ofSize(10));

        // then
        assertThat(users).hasSize(4)
                .contains(user01, user02, user03, user04);
    }


    @DisplayName("사용자 조회시 이름으로 조회할 수 있다")
    @Test
    void findUsersByRealName(){
        // given
        Team team1 = createTeamForTest("test team1");
        Team team2 = createTeamForTest("test team2");

        final String password = UUID.randomUUID().toString();
        User user01 = createTeamForTest("00001", "홍길동", password, team1);
        User user02 = createTeamForTest("00002", "류관순", password, team1);
        User user03 = createTeamForTest("00003", "장길산", password, team1);
        User user04 = createTeamForTest("00004", "임꺽정", password, team2);

        FindUsersRequest request = FindUsersRequest.builder().realName("길").build();

        // when
        Page<User> users = userRepository.findUsers(request, Pageable.ofSize(10));

        // then
        assertThat(users).hasSize(2)
                .contains(user01, user03)
                .doesNotContain(user02, user04);
    }

    @DisplayName("사용자 조회시 Team으로 조회할 수 있다")
    @Test
    void findUsersByTeamId(){
        // given
        Team team1 = createTeamForTest("test team1");
        Team team2 = createTeamForTest("test team2");

        final String password = UUID.randomUUID().toString();
        User user01 = createTeamForTest("00001", "홍길동", password, team1);
        User user02 = createTeamForTest("00002", "류관순", password, team1);
        User user03 = createTeamForTest("00003", "장길산", password, team1);
        User user04 = createTeamForTest("00004", "임꺽정", password, team2);

        FindUsersRequest request = FindUsersRequest.builder().teamId(team1.getId()).build();

        // when
        Page<User> users = userRepository.findUsers(request, Pageable.ofSize(10));

        // then
        assertThat(users).hasSize(3)
                .contains(user01, user02, user03)
                .doesNotContain(user04);
    }

    @NotNull
    private Team createTeamForTest(String teamName) {
        return teamRepository.save(
                Team.builder()
                        .teamName(teamName)
                        .build()
        );
    }

    private User createTeamForTest(String username, String realName, String password, Team team) {
        return userRepository.save(
                User.builder()
                        .username(username)
                        .realName(realName)
                        .password(password)
                        .team(team)
                        .build()
        );
    }


}