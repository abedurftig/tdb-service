package org.tdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tdb.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private TestSuiteRepository testSuiteRepository;

    public ProjectDTO createProject(Account account, String projectName) {
        Project project = projectRepository.save(new Project(account, projectName));
        return ModelMapperImpl.getProjectDTO(project);
    }

    public List<ProjectDTO> getAccountProjects(Long accountId) {

        List<Project> projects = projectRepository.findByAccountId(accountId);
        return projects.size() > 0 ?
                ModelMapperImpl.getProjectDTOs(projects) : new ArrayList<>();

    }

    public List<TestRunDTO> getProjectTestRuns(Long projectId) {

        List<TestRun> testRuns = testRunRepository.findByProjectId(projectId);
        return testRuns.size() > 0 ?
                ModelMapperImpl.getTestRunDTOs(testRuns) : new ArrayList<>();

    }

    public TestSuiteDTO saveTestSuite(TestSuite testSuite) {
        return ModelMapperImpl.getTestSuiteDTO(testSuiteRepository.save(testSuite));
    }

    public TestRun getOrCreateTestRunByExternalId(String externalProjectId, String testRunExternalId) {

        TestRun testRun = testRunRepository.findByProjectExternalIdAndExternalId(externalProjectId, testRunExternalId)
                .orElseGet(() -> {

                    Project project = getProjectByExternalId(externalProjectId);
                    return testRunRepository.save(
                            new TestRun(project, getTestRunName(project.getId()), testRunExternalId));

                });

        return testRun;

    }

    private Project getProjectByExternalId(String externalProjectId) {
        return projectRepository.findByExternalId(externalProjectId).get();
    }

    private String getTestRunName(Long projectId) {
        return "Test Run (P " + projectId + ") - " + new Date().toString();
    }

}
