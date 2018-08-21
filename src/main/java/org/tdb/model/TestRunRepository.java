package org.tdb.model;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestRunRepository extends JpaRepository<TestRun, Long> {

    Optional<TestRun> findByProjectExternalIdAndExternalId(String projectExternalId, String testRunExternalId);

    Optional<TestRun> findByProjectIdAndExternalId(Long projectId, String testRunExternalId);

    Optional<TestRun> findByExternalId(String testRunExternalId);

    List<TestRun> findByProjectId(Long projectId);

    List<TestRun> findByProjectIdOrderByCreatedDateDesc(Long projectId, Pageable pageable);

    default List<TestRun> findByProjectIdLast(Long projectId, int count) {
        return findByProjectIdOrderByCreatedDateDesc(projectId, PageRequest.of(0, count));
    }

}
