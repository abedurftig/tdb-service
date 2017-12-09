package org.testdashboard.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Arne
 * @since 09/12/2017
 */
public interface TestCaseRepository  extends JpaRepository<TestCase, Long> {
}
