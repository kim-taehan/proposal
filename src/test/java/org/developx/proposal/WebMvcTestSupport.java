package org.developx.proposal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.developx.proposal.domain.customer.service.CustomerService;
import org.developx.proposal.domain.project.service.ProjectService;
import org.developx.proposal.web.customer.CustomerController;
import org.developx.proposal.web.project.ProjectController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
        CustomerController.class,
        ProjectController.class
})
public abstract class WebMvcTestSupport {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected CustomerService customerService;

    @MockBean
    protected ProjectService projectService;
}
