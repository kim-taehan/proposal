package org.developx.proposal.global.infra.jpa;

import lombok.RequiredArgsConstructor;
import org.developx.proposal.global.security.CustomUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Entity의 값이 생성 되고 변경 될 때 누가 만들었는지, 누가 수정했는지 까지 자동으로 값을 업데이트 해주는 기능
 */
@Component
@RequiredArgsConstructor
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        return Optional.ofNullable(authentication.getName());
    }

}
