package org.tdb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdb.model.*;
import org.tdb.service.ProjectService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

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

    public List<ProjectDTO> getAccountProjectsSummary(Long accountId) {

        List<Project> projects = projectRepository.findByAccountId(accountId);
        List<ProjectDTO> projectDTOs = new ArrayList<>();

        for (Project p : projects) {
            projectDTOs.add(ModelMapperImpl.getSimpleProjectDTO(p));
        }

        return projectDTOs;

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

    public ProjectDTO getProjectDTO(Long projectId) {
        return ModelMapperImpl.getProjectDTO(getProject(projectId));
    }

    public Project getProject(Long projectId) {
        return projectRepository.findOne(projectId);
    }

    private Project getProjectByExternalId(String externalProjectId) {
        return projectRepository.findByExternalId(externalProjectId).get();
    }

    private String getTestRunName(Long projectId) {
        return "Test Run (P " + projectId + ") - " + new Date().toString();
    }
}
