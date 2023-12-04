package org.developx.proposal.web.proposal;

import org.developx.proposal.WebMvcTestSupport;
import org.developx.proposal.domain.proposal.data.ContextDto;
import org.developx.proposal.domain.proposal.data.Contexts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;

import java.nio.file.Path;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[controller] proposals controller")
class ProposalControllerTest extends WebMvcTestSupport {

    @DisplayName("신규 제안서 등록 폼을 조회한다.")
    @Test
    void createForm() throws Exception {

        // when & then
        mockMvc.perform(get("/proposals/new")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("제안서 조회 폼을 조회한다.")
    @Test
    void proposals() throws Exception {

        // given
        Page<Contexts> contexts = Page.empty();
        when(contextService.findContexts(any())).thenReturn(contexts);

        // when & then
        mockMvc.perform(get("/proposals")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
    @DisplayName("제안서 상세내역을 조회한다.")
    @Test
    void detail() throws Exception {

        when(contextService.findContext(any())).thenReturn(null);

        // when & then
        mockMvc.perform(get("/proposals/1")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

}