package org.tdb.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestRunRepository extends JpaRepository<TestRun, Long> {

    Optional<TestRun> findByProjectExternalIdAndExternalId(String projectExternalId, String testRunExternalId);

    Optional<TestRun> findByProjectIdAndExternalId(Long projectId, String testRunExternalId);

    Optional<TestRun> findByExternalId(String testRunExternalId);

    List<TestRun> findByProjectId(Long projectId);

}
