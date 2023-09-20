package org.developx.proposal.domain.user.repository.querydsl;

import org.developx.proposal.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryQueryDsl {
    Page<User> findUsers(Pageable pageable);
}
