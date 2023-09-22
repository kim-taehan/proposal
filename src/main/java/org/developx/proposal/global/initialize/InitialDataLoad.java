package org.developx.proposal.global.initialize;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.developx.proposal.domain.project.entity.Project;
import org.developx.proposal.domain.user.data.enums.TeamType;
import org.developx.proposal.domain.user.entity.Team;
import org.developx.proposal.domain.user.entity.User;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class InitialDataLoad {

    @PersistenceContext
    private EntityManager em;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initData() {
        Team 이노베이션1팀 = Team.builder()
                .teamName("이노베이션1팀")
                .teamType(TeamType.TEAM).build();
        Team 이노베이션2팀 = Team.builder()
                .teamName("이노베이션2팀")
                .teamType(TeamType.TEAM).build();
        em.persist(이노베이션1팀);
        em.persist(이노베이션2팀);


        User user1 = User.builder()
                .username("08001")
                .password("08001")
                .realName("아이유")
                .team(이노베이션1팀)
                .build();

        User user2 = User.builder()
                .username("08002")
                .password("08002")
                .realName("권나라")
                .team(이노베이션1팀)
                .build();

        User user3 = User.builder()
                .username("08003")
                .password("08003")
                .realName("조여정")
                .team(이노베이션1팀)
                .build();

        User user4 = User.builder()
                .username("08004")
                .password("08004")
                .realName("박보영")
                .team(이노베이션2팀)
                .build();

        em.persist(user1);
        em.persist(user2);
        em.persist(user3);
        em.persist(user4);

        Project project = new Project("NH 채널 고도화", "NH 손해보험");
        em.persist(project);
    }


}
