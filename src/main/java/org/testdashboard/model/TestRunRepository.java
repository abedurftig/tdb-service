package org.testdashboard.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Arne
 * @since 28/11/2017
 */
public interface TestRunRepository extends JpaRepository<TestRun, Long> {

    public Optional<TestRun> findByProjectExternalIdAndExternalId(String projectExternalId, String testRunExternalId);

    public Optional<TestRun> findByProjectIdAndExternalId(Long projectId, String testRunExternalId);

}
