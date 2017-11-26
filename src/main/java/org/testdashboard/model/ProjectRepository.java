package org.testdashboard.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {

    public List<Project> findByAccountId(Long accountId);

}
