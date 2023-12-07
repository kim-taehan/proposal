package org.developx.proposal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.developx.proposal.domain.bookmark.service.BookmarkService;
import org.developx.proposal.domain.customer.service.CustomerService;
import org.developx.proposal.domain.project.service.ProjectService;
import org.developx.proposal.domain.proposal.service.ContextServiceImpl;
import org.developx.proposal.domain.proposal.service.ProposalService;
import org.developx.proposal.domain.proposal.service.SlideService;
import org.developx.proposal.domain.user.service.TeamService;
import org.developx.proposal.domain.user.service.UserService;
import org.developx.proposal.web.bookmark.BookmarkController;
import org.developx.proposal.web.customer.CustomerController;
import org.developx.proposal.web.project.ProjectController;
import org.developx.proposal.web.proposal.ProposalController;
import org.developx.proposal.web.user.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
        CustomerController.class,
        ProjectController.class,
        UserController.class,
        ProposalController.class,
        BookmarkController.class
}
//,excludeAutoConfiguration = SecurityAutoConfiguration.class
)
@WithMockUser(username = "USER", roles = "USER")
public abstract class WebMvcTestSupport {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @MockBean
    protected CustomerService customerService;
    @MockBean
    protected ProjectService projectService;
    @MockBean
    protected UserService userService;
    @MockBean
    protected ProposalService proposalService;
    @MockBean
    protected SlideService slideService;
    @MockBean
    protected ContextServiceImpl contextService;
    @MockBean
    protected BookmarkService bookmarkService;
    @MockBean
    protected TeamService teamService;

}
