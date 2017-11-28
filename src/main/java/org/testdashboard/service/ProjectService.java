package org.testdashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testdashboard.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Arne
 * @since 24/11/2017
 */
@Component
public class ProjectService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TestRunRepository testRunRepository;

    public List<ProjectDTO> getAccountProjects(Long accountId) {

        List<Project> projects = projectRepository.findByAccountId(accountId);
        return projects.size() > 0 ?
                ModelMapperImpl.getProjectDTOs(projects) : new ArrayList<>();

    }

    public TestSuiteDTO addTestSuiteToProjectAndTestRun(
            String externalProjectId, String externalTestRunId, TestSuiteDTO testSuiteDTO) {

        Project project = getProjectByExternalId(1L, externalProjectId);
        TestRun testRun = getOrCreateTestRunByExternalId(externalProjectId, externalTestRunId);

        return null;

    }

    private Project getProjectByExternalId(Long accountId, String externalProjectId) {
        return projectRepository.findByExternalId(externalProjectId).get();
    }

    private TestRun getOrCreateTestRunByExternalId(String externalProjectId, String testRunExternalId) {

        TestRun testRun = testRunRepository.findByProjectExternalIdAndExternalId(externalProjectId, testRunExternalId)
                .orElseGet(() -> {

                    Project project = projectRepository.findByExternalId(externalProjectId).get();
                    return testRunRepository.save(
                            new TestRun(project, getTestRunName(project.getId()), testRunExternalId));

                });

        return testRun;

    }

    private String getTestRunName(Long projectId) {
        return "Test Run (P " + projectId + ") - " + new Date().toString();
    }

}
