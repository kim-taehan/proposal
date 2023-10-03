package org.developx.proposal.web.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.developx.proposal.WebMvcTestSupport;
import org.developx.proposal.domain.customer.data.enums.CustomerType;
import org.developx.proposal.domain.customer.data.response.FindCustomersResponse;
import org.developx.proposal.domain.project.data.response.FindProjectsResponse;
import org.developx.proposal.web.project.data.CreateProjectForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[controller] customer controller")
class ProjectControllerTest extends WebMvcTestSupport {

    @DisplayName("등록된 프로젝트 리스트를 조회한다.")
    @Test
    void projects() throws Exception {
        // given
        Page<FindProjectsResponse> result = Page.empty();
        when(projectService.findProjects(any(), any())).thenReturn(result);

        mockMvc.perform(get("/projects")
                        .queryParam("projectName", "")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("신규 프로젝트 등록 폼을 조회한다")
    @Test
    void createForm() throws Exception {
        // when & then
        mockMvc.perform(get("/projects/new")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("신규 프로젝트를 등록합니다.")
    @Test
    void createProject() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(post("/projects/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("projectName", "테스트고객")
                        .param("projectYear", "2023")
                        .param("customerId", "1")
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().hasNoErrors());
    }

    @DisplayName("신규 프로젝트를 등록시 projectName 필수로 입력해야 된다.")
    @Test
    void createProjectNoProjectName() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(post("/projects/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("projectYear", "2023")
                        .param("customerId", "1")
                )
                .andDo(print())
                .andExpect(status().isOk()) // 리다이렉트 수행됨
                .andExpect(model().errorCount(1))
                .andExpect(model().attributeHasFieldErrorCode("createProjectForm", "projectName", "NotBlank"));
    }

    @DisplayName("신규 프로젝트를 등록시 projectYear, customerId 필수로 입력해야 된다.")
    @Test
    void createProjectNoProjectYearAndCustomerId() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(post("/projects/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("projectName", "테스트고객")
                )
                .andDo(print())
                .andExpect(status().isOk()) // 리다이렉트 수행됨
                .andExpect(model().errorCount(2))
                .andExpect(model().attributeHasFieldErrorCode("createProjectForm", "projectYear", "NotBlank"))
                .andExpect(model().attributeHasFieldErrorCode("createProjectForm", "customerId", "NotNull"));
    }


}