package org.testdashboard.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Arne
 * @since 28/11/2017
 */
public interface TestRunRepository extends JpaRepository<TestRun, Long> {

    public TestRun findByProjectExternalIdAndExternalId(String projectExternalId, String testRunExternalId);

    public TestRun findByProjectIdAndExternalId(Long projectId, String testRunExternalId);

}
