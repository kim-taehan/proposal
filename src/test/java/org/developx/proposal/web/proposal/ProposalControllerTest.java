package org.developx.proposal.web.proposal;

import org.developx.proposal.WebMvcTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[controller] proposals controller")
class ProposalControllerTest extends WebMvcTestSupport {

    @DisplayName("신규 제안서 등록 폼을 조회한다")
    @Test
    void createForm() throws Exception {

        // when & then
        mockMvc.perform(get("/proposals/new")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}