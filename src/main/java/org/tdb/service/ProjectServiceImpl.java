package org.tdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdb.model.*;
import org.tdb.security.AccountSecurity;

import java.util.*;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    AccountSecurity accountSecurity;

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

    public List<ProjectSummaryDTO> getAccountProjectsSummary(Long accountId) {

        List<Project> projects = projectRepository.findByAccountId(accountId);
        List<ProjectSummaryDTO> projectSummaryDTOs = new ArrayList<>();

        for (Project p : projects) {
            projectSummaryDTOs.add(ModelMapperImpl.getProjectSummaryDTO(p));
        }

        return projectSummaryDTOs;

    }

    public List<TestRunDTO> getProjectTestRuns(Long projectId) {

        List<TestRun> testRuns = testRunRepository.findByProjectId(projectId);
        return testRuns.size() > 0 ?
                ModelMapperImpl.getTestRunDTOs(testRuns) : new ArrayList<>();

    }

    public TestSuiteDTO saveTestSuite(TestSuite testSuite) {
        return ModelMapperImpl.getTestSuiteDTO(testSuiteRepository.save(testSuite));
    }

    @Override
    public List<TestSuiteDTO> saveTestSuites(List<TestSuite> testSuites) {
        return ModelMapperImpl.getTestSuiteDTOs(testSuiteRepository.save(testSuites));
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

    public ProjectDTO getProjectDTO(Long projectId) throws ProjectServiceException {
        return ModelMapperImpl.getProjectDTO(getProject(projectId));
    }

    public Project getProject(Long projectId) throws ProjectServiceException {
        if (accountSecurity.hasAccessToProject(projectId)) {
            return projectRepository.findOne(projectId);
        } else {
            throw new ProjectServiceException(ProjectServiceException.ErrorCode.NOT_AUTHORIZED);
        }
    }

    @Override
    public List<TestRunSummaryDTO> getProjectTestRunsSummary(Long projectId) throws ProjectServiceException {

        if (accountSecurity.hasAccessToProject(projectId)) {
            List<TestRunSummaryDTO> testRunSummaryDTOs =
                    new ArrayList<>();

            List<TestRun> testRuns = testRunRepository.findByProjectId(projectId);
            testRuns.forEach(testRun ->
                    testRunSummaryDTOs.add(ModelMapperImpl.getTestRunSummaryDTO(testRun))
            );

            return testRunSummaryDTOs;
        } else {
            throw new ProjectServiceException(ProjectServiceException.ErrorCode.NOT_AUTHORIZED);
        }

    }

    @Override
    public void deleteProject(Long projectId) throws ProjectServiceException {
        if (accountSecurity.hasAccessToProject(projectId)) {
            projectRepository.delete(projectId);
        } else {
            throw new ProjectServiceException(ProjectServiceException.ErrorCode.NOT_AUTHORIZED);
        }
    }

    private Project getProjectByExternalId(String externalProjectId) {
        return projectRepository.findByExternalId(externalProjectId).get();
    }

    private String getTestRunName(Long projectId) {
        return "Test Run (P " + projectId + ") - " + new Date().toString();
    }
}
