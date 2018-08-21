package org.tdb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdb.model.*;
import org.tdb.security.AccountSecurity;

import javax.crypto.KeyGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);

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
        String key = projectName;
        try {
            key = Base64.getEncoder().encodeToString(
                    KeyGenerator.getInstance("AES").generateKey().getEncoded());
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Unknown algorithm");
        }
        Project project = projectRepository.save(new Project(account, projectName, key));
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
        return ModelMapperImpl.getTestSuiteDTOs(testSuiteRepository.saveAll(testSuites));
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

            Optional<Project> projectOptional = projectRepository.findById(projectId);
            if (projectOptional.isPresent()) {
                return projectOptional.get();
            } else {
                throw ProjectServiceException.withDoesNotExist();
            }
        } else {
            throw ProjectServiceException.withNotAuthorized();
        }
    }

    @Override
    public List<TestRunSummaryDTO> getProjectTestRunsSummary(Long projectId) throws ProjectServiceException {

        if (accountSecurity.hasAccessToProject(projectId)) {

            List<TestRun> testRuns = testRunRepository.findByProjectId(projectId);
            List<TestRunSummaryDTO> testRunSummaryDTOs = testRuns.stream()
                    .map(testRun -> ModelMapperImpl.getTestRunSummaryDTO(testRun))
                    .collect(Collectors.toList());

            return testRunSummaryDTOs;

        } else {
            throw ProjectServiceException.withNotAuthorized();
        }

    }

    @Override
    public List<TestRunSummaryDTO> getProjectTestRunsSummary(Long projectId, int count) throws ProjectServiceException {

        if (accountSecurity.hasAccessToProject(projectId)) {

            List<TestRun> testRuns = testRunRepository.findByProjectIdLast(projectId, count);
            List<TestRunSummaryDTO> testRunSummaryDTOs = testRuns.stream()
                    .map(testRun -> ModelMapperImpl.getTestRunSummaryDTO(testRun))
                    .collect(Collectors.toList());

            return testRunSummaryDTOs;

        } else {
            throw ProjectServiceException.withNotAuthorized();
        }

    }

    @Override
    public ProjectSummaryDTO getProjectSummaryDTO(Long projectId) throws ProjectServiceException {
        if (accountSecurity.hasAccessToProject(projectId)) {
            Project project = projectRepository.getOne(projectId);
            return ModelMapperImpl.getProjectSummaryDTO(project);
        } else {
            throw ProjectServiceException.withNotAuthorized();
        }
    }

    @Override
    public void deleteProject(Long projectId) throws ProjectServiceException {
        if (accountSecurity.hasAccessToProject(projectId)) {
            projectRepository.deleteById(projectId);
        } else {
            throw ProjectServiceException.withNotAuthorized();
        }
    }

    private Project getProjectByExternalId(String externalProjectId) {
        return projectRepository.findByExternalId(externalProjectId).get();
    }

    private String getTestRunName(Long projectId) {
        return "Test Run (P " + projectId + ") - " + new Date().toString();
    }
}
