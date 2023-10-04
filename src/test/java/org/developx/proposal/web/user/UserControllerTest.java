package org.developx.proposal.web.user;

import org.developx.proposal.WebMvcTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends WebMvcTestSupport {

    @DisplayName("등록된 사용자 리스트를 조회한다.")
    @Test
    void users() throws Exception {
        // given
        when(userService.findUsers(any(), any())).thenReturn(Page.empty());

        // when
        // then
        mockMvc.perform(get("/users")
                        .queryParam("realName", "")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("신규 사용자 등록 폼을 조회한다")
    @Test
    void createForm() throws Exception {
        // when & then
        mockMvc.perform(get("/users/new")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("신규 사용자를 등록합니다.")
    @Test
    void createProject() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(post("/users/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "테스트고객")
                        .param("password", "test")
                        .param("realName", "테스트고객")
                        .param("teamId", "100")
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().hasNoErrors());
    }

    @DisplayName("신규 사용자 등록시 username가 없는 경우 에러가 발생한다.")
    @Test
    void createProjectNoUsername() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(post("/users/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("password", "test")
                        .param("realName", "테스트고객")
                        .param("teamId", "100")
                )
                .andDo(print())
                .andExpect(status().isOk()) // 리다이렉트 수행됨
                .andExpect(model().errorCount(1))
                .andExpect(model().attributeHasFieldErrorCode("createUserForm", "username", "NotBlank"));
    }

    @DisplayName("신규 사용자 등록시 필수 데이터들을 확인한다.")
    @Test
    void createProjectNoColumn() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(post("/users/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andDo(print())
                .andExpect(status().isOk()) // 리다이렉트 수행됨
                .andExpect(model().errorCount(4))
                .andExpect(model().attributeHasFieldErrorCode("createUserForm", "username", "NotBlank"))
                .andExpect(model().attributeHasFieldErrorCode("createUserForm", "password", "NotBlank"))
                .andExpect(model().attributeHasFieldErrorCode("createUserForm", "realName", "NotBlank"))
                .andExpect(model().attributeHasFieldErrorCode("createUserForm", "teamId", "NotNull"));
    }

    @DisplayName("신규 사용자 등록시 team id는 양수 데이터야 한다.")
    @Test
    void createProjectTeamIdPositive() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(post("/users/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "테스트고객")
                        .param("password", "test")
                        .param("realName", "테스트고객")
                        .param("teamId", "-100")
                )
                .andDo(print())
                .andExpect(status().isOk()) // 리다이렉트 수행됨
                .andExpect(model().errorCount(1))
                .andExpect(model().attributeHasFieldErrorCode("createUserForm", "teamId", "Positive"));
    }

}