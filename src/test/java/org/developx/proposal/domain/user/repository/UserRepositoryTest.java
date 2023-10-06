package org.developx.proposal.domain.user.repository;

import org.developx.proposal.DataJpaTestSupport;
import org.developx.proposal.domain.user.entity.Privacy;
import org.developx.proposal.domain.user.entity.Team;
import org.developx.proposal.domain.user.entity.User;
import org.developx.proposal.domain.user.entity.enums.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[repository] user repository")
class UserRepositoryTest extends DataJpaTestSupport {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PrivacyRepository privacyRepository;
    @Autowired
    private UserRepository userRepository;

    @DisplayName("사용자 ID로 개인정보, 팀정보등도 같이 조회한다.")
    @Test
    void findByUser(){
        // given
        final String password = UUID.randomUUID().toString();
        Team team = teamRepository.save(Team.builder().teamName("test team").build());
        Privacy privacy = privacyRepository.save(Privacy.builder()
                .birth(LocalDate.of(1999, 12, 31))
                .email("admin@sk.com")
                .gender(Gender.MALE)
                .mobile("01012345678")
                .address("서울시 강남구")
                .build()
        );
        User user = userRepository.save(User.builder()
                .username("00001")
                .realName("홍길동")
                .password(password)
                .team(team)
                .privacy(privacy)
                .build()
        );

        // when
        User findUser = userRepository.findByUser(user.getId());

        // then
        assertThat(findUser).isNotNull()
                .extracting("username", "realName",
                        "team.teamName", "team.id",
                        "privacy.id", "privacy.address", "privacy.birth", "privacy.email", "privacy.gender", "privacy.mobile")
                .contains(user.getUsername(), user.getRealName(),
                        team.getTeamName(), team.getId(),
                        privacy.getId(), privacy.getAddress(), privacy.getBirth(), privacy.getEmail(), privacy.getGender(), privacy.getMobile()
                );
    }


}