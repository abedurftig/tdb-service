package org.tdb.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByAccountId(Long accountId);

    Optional<Project> findByExternalId(String externalId);

    @Query("SELECT p.account FROM Project p WHERE p.id = :projectId")
    Account getOwningAccount(@Param("projectId") Long projectId);

}
