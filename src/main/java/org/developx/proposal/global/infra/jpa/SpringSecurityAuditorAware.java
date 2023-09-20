package org.developx.proposal.global.infra.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
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
        return Optional.empty();
    }

}
