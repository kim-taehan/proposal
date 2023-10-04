package org.developx.proposal.web.user.data;

import org.developx.proposal.domain.user.data.request.FindUsersRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[record] users form")
class UsersFormTest {

    @DisplayName("UsersForm 데이터를 FindUserRequest 객체로 변경할 수 있다.")
    @Test
    void toFindUserRequest(){
        // given
        UsersForm usersForm = UsersForm.builder()
                .realName("test name")
                .teamId(100l)
                .build();

        // when
        FindUsersRequest request = usersForm.toFindUserRequest();

        // then
        assertThat(request).isNotNull()
                .extracting("realName", "teamId")
                .contains("test name", 100l);
    }


}