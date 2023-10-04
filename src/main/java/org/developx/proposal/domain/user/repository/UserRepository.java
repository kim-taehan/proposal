package org.developx.proposal.domain.user.repository;

import org.developx.proposal.domain.user.entity.User;
import org.developx.proposal.domain.user.repository.querydsl.UserRepositoryQueryDsl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryQueryDsl {

    Optional<User> findByUsername(String username);

}
