package org.developx.proposal.web.user.data;

import org.developx.proposal.domain.user.data.request.CreateUserRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[record] create user form")
class CreateUserFormTest {

    @DisplayName("CreateUserForm 객체를 CreateUserRequest 객체로 변경한다")
    @Test
    void toCreateUserRequest(){
        // given
        String password = UUID.randomUUID().toString();
        CreateUserForm form = CreateUserForm.builder()
                .username("00001")
                .password(password)
                .realName("홍길동")
                .teamId(100L)
                .build();

        // when
        CreateUserRequest request = form.toCreateUserRequest();

        // then
        assertThat(request)
                .extracting("username", "password", "realName", "teamId")
                .contains(form.username(), form.password(), form.realName(), form.teamId());
    }

}