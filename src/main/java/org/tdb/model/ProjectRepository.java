package org.tdb.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {

    public List<Project> findByAccountId(Long accountId);

    public Optional<Project> findByExternalId(String externalId);

    @Query("SELECT p.account FROM Project p WHERE p.id = :projectId")
    public Account getOwningAccount(@Param("projectId") Long projectId);

}
