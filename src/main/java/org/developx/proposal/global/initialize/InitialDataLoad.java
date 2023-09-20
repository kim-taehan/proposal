package org.developx.proposal.global.initialize;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.developx.proposal.domain.user.entity.User;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class InitialDataLoad {

    @PersistenceContext
    private EntityManager em;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initData() {
        User user1 = User.builder()
                .username("08001")
                .password("08001")
                .realName("아이유")
                .build();

        User user2 = User.builder()
                .username("08002")
                .password("08002")
                .realName("권나라")
                .build();

        User user3 = User.builder()
                .username("08003")
                .password("08003")
                .realName("조여정")
                .build();

        User user4 = User.builder()
                .username("08004")
                .password("08004")
                .realName("박보영")
                .build();
        em.persist(user1);
        em.persist(user2);
        em.persist(user3);
        em.persist(user4);
    }


}
