package org.developx.proposal.web.bookmark;

import org.developx.proposal.WebMvcTestSupport;
import org.developx.proposal.domain.customer.data.enums.CustomerType;
import org.developx.proposal.domain.proposal.entity.Slide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("[controller] bookmark controller")
class BookmarkControllerTest extends WebMvcTestSupport {

    @DisplayName("신규 북마크를 추가한다.")
    @Test
    void createCustomer() throws Exception {
        // given
        when(slideService.findById(anyInt())).thenReturn(Slide.builder().build());

        // when
        // then
        mockMvc.perform(post("/bookmarks/add")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("slideId", "1").with(csrf())
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().hasNoErrors());
    }
}