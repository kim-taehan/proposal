package org.developx.proposal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.developx.proposal.domain.customer.service.CustomerService;
import org.developx.proposal.web.customer.CustomerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
        CustomerController.class
})
public abstract class WebMvcTestSupport {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected CustomerService customerService;
}
