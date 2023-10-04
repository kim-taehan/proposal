package org.developx.proposal.domain.user.data.request;

import org.developx.proposal.SpringBootTestSupport;
import org.developx.proposal.domain.user.entity.Team;
import org.developx.proposal.domain.user.entity.User;
import org.developx.proposal.domain.user.repository.TeamRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[record] create user request")
class CreateUserRequestTest extends SpringBootTestSupport {

    @Autowired
    private TeamRepository teamRepository;
    @DisplayName("CreateUserRequest 를 user entity로 변환한다.")
    @Test
    void toEntity() {
        // given
        Team team = teamRepository.save(
                Team.builder()
                        .teamName("테스트 Team")
                        .build()
        );

        final String password = UUID.randomUUID().toString();
        CreateUserRequest request = CreateUserRequest.builder()
                .username("00001")
                .realName("홍길동")
                .password(password)
                .teamId(team.getId())
                .build();

        // when
        User user = request.toEntity(team);

        // then
        assertThat(user).isNotNull()
                .extracting("username", "password", "realName", "team")
                .contains(request.username(), request.password(), request.realName(), team);

    }
}