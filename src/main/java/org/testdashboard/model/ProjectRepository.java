package org.testdashboard.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {

    public List<Project> findByAccountId(Long accountId);

    public Optional<Project> findByExternalId(String externalId);

}
