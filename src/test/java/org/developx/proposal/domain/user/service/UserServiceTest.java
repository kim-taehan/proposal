package org.developx.proposal.domain.user.service;

import org.assertj.core.groups.Tuple;
import org.developx.proposal.SpringBootTestSupport;
import org.developx.proposal.domain.user.data.request.CreateUserRequest;
import org.developx.proposal.domain.user.data.request.FindUsersRequest;
import org.developx.proposal.domain.user.data.response.FindUserResponse;
import org.developx.proposal.domain.user.data.response.UsersResponse;
import org.developx.proposal.domain.user.entity.Privacy;
import org.developx.proposal.domain.user.entity.Team;
import org.developx.proposal.domain.user.entity.User;
import org.developx.proposal.domain.user.entity.enums.Gender;
import org.developx.proposal.domain.user.repository.PrivacyRepository;
import org.developx.proposal.domain.user.repository.TeamRepository;
import org.developx.proposal.domain.user.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
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
    @Autowired
    private PrivacyRepository privacyRepository;

    @DisplayName("사용자 조회시 조건 없는 경우 전체가 조회된다.")
    @Test
    void findUsers(){
        // given
        Team team1 = createUserForTest("test team1");
        Team team2 = createUserForTest("test team2");

        final String password = UUID.randomUUID().toString();
        createUserForTest("00001", "홍길동", password, team1, null);
        createUserForTest("00002", "류관순", password, team1, null);
        createUserForTest("00003", "장길산", password, team1, null);
        createUserForTest("00004", "임꺽정", password, team2, null);

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
        Team team1 = createUserForTest("test team1");
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
                .extracting("username", "realName", "team")
                .contains(Tuple.tuple(request.username(), request.realName(), team1));
    }

    @NotNull
    private Team createUserForTest(String teamName) {
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

    private User createUserForTest(String username, String realName, String password, Team team, Privacy privacy) {
        return userRepository.save(
                User.builder()
                        .username(username)
                        .realName(realName)
                        .password(password)
                        .team(team)
                        .privacy(privacy)
                        .build()
        );
    }

    @DisplayName("사용자 ID로 개인정보, 팀정보등도 같이 조회한다.")
    @Test
    void findUser(){
        // given
        final String password = UUID.randomUUID().toString();
        Team team = createUserForTest("test team");
        Privacy privacy = privacyRepository.save(Privacy.builder()
                .birth(LocalDate.of(1999, 12, 31))
                .email("admin@sk.com")
                .gender(Gender.MALE)
                .mobile("01012345678")
                .address("서울시 강남구")
                .build());
        User user = createUserForTest("00001", "홍길동", password, team, privacy);
        entityManager.flush();
        entityManager.clear();

        // when
        FindUserResponse findUserResponse = userService.findUser(user.getId());

        // then
        assertThat(findUserResponse).isNotNull()
                .extracting("username", "realName",
                        "team.teamName", "team.teamId",
                        "privacy.privacyId", "privacy.address", "privacy.birth", "privacy.email", "privacy.gender", "privacy.mobile")
                .contains(user.getUsername(), user.getRealName(),
                        team.getTeamName(), team.getId(),
                        privacy.getId(), privacy.getAddress(), privacy.getBirth(), privacy.getEmail(), privacy.getGender(), privacy.getMobile()
                );
    }

    @DisplayName("사용자 ID로 상세 조회시 개인정보가 없는 경우에 애러가 발생하지 않는다.")
    @Test
    void findUserNoPrivacy(){
        // given
        final String password = UUID.randomUUID().toString();
        Team team = createUserForTest("test team");
                        User user = createUserForTest("00001", "홍길동", password, team, null);
        entityManager.flush();
        entityManager.clear();

        // when
        FindUserResponse findUserResponse = userService.findUser(user.getId());

        // then
        assertThat(findUserResponse).isNotNull()
                .extracting("username", "realName",
                        "team.teamName", "team.teamId")
                .contains(user.getUsername(), user.getRealName(),
                        team.getTeamName(), team.getId()
                );
    }


}