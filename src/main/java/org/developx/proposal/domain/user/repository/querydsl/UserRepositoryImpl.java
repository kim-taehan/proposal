package org.developx.proposal.domain.user.repository.querydsl;

import org.developx.proposal.domain.user.entity.QUser;
import org.developx.proposal.domain.user.entity.User;
import org.developx.proposal.global.infra.Querydsl4RepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.developx.proposal.domain.user.entity.QUser.user;

public class UserRepositoryImpl extends Querydsl4RepositorySupport implements UserRepositoryQueryDsl {

    public UserRepositoryImpl() {
        super(User.class);
    }
    @Override
    public Page<User> findUsers(Pageable pageable) {
        return applyPagination(pageable,
                contentQuery -> contentQuery
                        .selectFrom(user)
                        .where(

                        )
        );
    }
}
