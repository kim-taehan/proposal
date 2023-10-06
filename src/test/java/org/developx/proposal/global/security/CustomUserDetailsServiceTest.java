package org.developx.proposal.global.security;

import org.developx.proposal.SpringBootTestSupport;
import org.developx.proposal.domain.user.entity.enums.Role;
import org.developx.proposal.domain.user.entity.User;
import org.developx.proposal.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.Assertions.*;

@DisplayName("[security] implement user details service ")
class CustomUserDetailsServiceTest extends SpringBootTestSupport {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserRepository userRepository;

    @DisplayName("등록된 username 으로 UserDetails 객체를 생성한다.")
    @Test
    void loadUserByUsername(){
        // given
        User user = userRepository.save(User.builder()
                .username("00001")
                .realName("홍길동")
                .password("test001")
                .role(Role.USER)
                .build()
        );

        // when
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());
        // then
        assertThat(userDetails).isNotNull()
                .extracting("username", "password")
                .contains(user.getUsername(), user.getPassword());

        assertThat(userDetails.getAuthorities()).isNotNull()
                .hasSize(1)
                .extracting("authority");
    }

    @DisplayName("등록되지 않은 username 인 경우 에러가 발생한다.")
    @Test
    void loadUserByUsernameByNotSaveUsername(){
        // given
        // when
        // then
        assertThatThrownBy(() -> customUserDetailsService.loadUserByUsername("00001"))
                .isInstanceOf(AuthenticationServiceException.class)
                .hasMessage("등록된 사용자가 아닙니다.");
    }

}