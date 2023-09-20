package org.developx.proposal.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.developx.proposal.domain.user.data.TeamDto;
import org.developx.proposal.domain.user.entity.Team;
import org.developx.proposal.domain.user.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamService {

    private final TeamRepository teamRepository;

    public Optional<Team> findById(long teamId) {
        return teamRepository.findById(teamId);
    }

    public List<TeamDto> findAllTeam() {
        return teamRepository.findAll().stream()
                .map(TeamDto::from)
                .toList();
    }


}
