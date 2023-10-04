package org.developx.proposal.domain.user.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("[entity] user entity")
class UserTest {

    @DisplayName("사용자 생성시에 username에 숫자가 아닌 데이터는 입력할 수 없다.")
    @Test
    void UserCheckNumber(){
        // given
        // when
        User.UserBuilder userBuilder = User.builder()
                .username("0001A");

        // then
        assertThatThrownBy(() -> userBuilder.build()).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("username 은 숫자만 5자리를 입력받아야 합니다.");
    }

    @DisplayName("사용자 생성시에 username는 5자리로 구성되어야 한다")
    @Test
    void UserCheckCounting(){
        // given
        // when
        User.UserBuilder userBuilder = User.builder()
                .username("0001");

        // then
        assertThatThrownBy(() -> userBuilder.build()).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("username 은 숫자만 5자리를 입력받아야 합니다.");
    }

}