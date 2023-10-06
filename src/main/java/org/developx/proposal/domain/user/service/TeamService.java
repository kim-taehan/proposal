package org.developx.proposal.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.developx.proposal.domain.user.data.response.TeamDto;
import org.developx.proposal.domain.user.entity.Team;
import org.developx.proposal.domain.user.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamService {

    private final TeamRepository teamRepository;

    public Team findById(long teamId) {
        return teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException());
    }

    public List<TeamDto> findAllTeam() {
        return teamRepository.findAll().stream()
                .map(TeamDto::from)
                .toList();
    }


}
