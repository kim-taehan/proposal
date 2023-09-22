package org.developx.proposal.domain.project.repository;

import org.developx.proposal.domain.project.repository.querydsl.ProjectRepositoryQueryDsl;
import org.developx.proposal.domain.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, ProjectRepositoryQueryDsl {
}
