package org.developx.proposal.web.customer;

import org.developx.proposal.WebMvcTestSupport;
import org.developx.proposal.domain.customer.data.enums.CustomerType;
import org.developx.proposal.domain.customer.data.response.FindCustomersResponse;
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


@DisplayName("[controller] customer controller")
class CustomerControllerTest extends WebMvcTestSupport {

    @DisplayName("등록된 고객사 리스트를 조회한다.")
    @Test
    void customers() throws Exception {
        // given
        Page<FindCustomersResponse> result = Page.empty();
        when(customerService.findCustomers(any(), any())).thenReturn(result);

        mockMvc.perform(get("/customers")
                        .queryParam("customerName", "")
                        .queryParam("customerType", CustomerType.BANK.name())
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("신규 고객사 등록 폼을 조회한다")
    @Test
    void createForm() throws Exception {
        // when & then
        mockMvc.perform(get("/customers/new")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }


    @DisplayName("신규 고객사를 등록합니다.")
    @Test
    void createCustomer() throws Exception {
        // given

        // when
        // then
        mockMvc.perform(post("/customers/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("customerName", "테스트고객")
                        .param("customerType", CustomerType.BANK.name())
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().hasNoErrors());
    }

    @DisplayName("신규 고객사 등록시 고객사 명은 필수로 입력해야 된다.")
    @Test
    void createCustomerNoCustomerName() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(post("/customers/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("customerName", "")
                        .param("customerType", CustomerType.BANK.name())
                )
                .andDo(print())
                .andExpect(status().isOk()) // 리다이렉트 수행됨
                .andExpect(model().errorCount(1))
                .andExpect(model().attributeHasFieldErrorCode("createCustomerForm", "customerName", "NotBlank"));
    }

    @DisplayName("신규 고객사 등록시 고객사 유형은 필수로 입력해야 된다.")
    @Test
    void createCustomerNoCustomerType() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(post("/customers/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("customerName", "테스트고객")
                )
                .andDo(print())
                .andExpect(status().isOk()) // 리다이렉트 수행됨
                .andExpect(model().errorCount(1))
                .andExpect(model().attributeHasFieldErrorCode("createCustomerForm", "customerType", "NotNull"));
    }


}