package org.developx.proposal.domain.user.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.developx.proposal.domain.user.data.request.FindUsersRequest;
import org.developx.proposal.domain.user.entity.User;
import org.developx.proposal.global.infra.Querydsl4RepositorySupport;
import org.developx.proposal.global.utils.QueryDslUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import static org.developx.proposal.domain.user.entity.QUser.user;

public class UserRepositoryImpl extends Querydsl4RepositorySupport implements UserRepositoryQueryDsl {

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public Page<User> findUsers(FindUsersRequest request, Pageable pageable) {
        return applyPagination(pageable,
                contentQuery -> contentQuery
                        .select(user)
                        .from(user)
                        .where(
                                realNameLike(request.realName()),
                                teamIdEq(request.teamId())
                        )
        );
    }

    private static BooleanExpression realNameLike(String realName) {
        return StringUtils.hasText(realName) ? user.realName.like(QueryDslUtils.makeLikeText(realName)) : null;
    }

    private static BooleanExpression teamIdEq(Long teamId) {
        return QueryDslUtils.positive(teamId) ? user.team.id.eq(teamId) : null;
    }
}
