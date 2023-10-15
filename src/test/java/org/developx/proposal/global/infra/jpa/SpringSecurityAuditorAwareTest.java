package org.developx.proposal.global.infra.jpa;

import org.developx.proposal.SpringBootTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[security] spring security auditor")
class SpringSecurityAuditorAwareTest extends SpringBootTestSupport {

    @Autowired
    private SpringSecurityAuditorAware auditorAware;
    @DisplayName("Table 입력시에 createBy, modifiedBy 에 들어가는 사용자를 조회한다.")
    @WithMockUser(username = "USER", roles = "USER")
    @Test
    void getCurrentAuditor(){
        // given
        // when
        String currentAuditor = auditorAware.getCurrentAuditor()
                .orElseThrow();

        // then
        assertThat(currentAuditor).isEqualTo("USER");
    }
}