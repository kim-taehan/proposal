package org.developx.proposal.global.security;

import org.developx.proposal.SpringBootTestSupport;
import org.developx.proposal.domain.user.data.enums.Role;
import org.developx.proposal.domain.user.entity.User;
import org.developx.proposal.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[security] implement user details")
class CustomUserDetailsTest extends SpringBootTestSupport {

    @Autowired
    private UserRepository userRepository;

    @DisplayName("CustomUserDetails 를 User 객체를 가지고 생성한다.")
    @Test
    void CustomUserDetails(){
        // given
        User user = userRepository.save(User.builder()
                .username("00001")
                .realName("홍길동")
                .password("test001")
                .role(Role.USER)
                .build()
        );

        // when
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        // then
        assertThat(customUserDetails).isNotNull()
                .extracting("username", "password")
                .containsExactlyInAnyOrder(user.getUsername(), user.getPassword());
    }


}