package org.tdb.service;

import org.tdb.model.*;

import java.util.List;

public interface ProjectService {

    ProjectDTO createProject(Account account, String projectName);

    List<ProjectDTO> getAccountProjects(Long accountId);

    List<ProjectSummaryDTO> getAccountProjectsSummary(Long accountId);

    List<TestRunDTO> getProjectTestRuns(Long projectId);

    List<TestRunSummaryDTO> getProjectTestRunsSummary(Long projectId);

    TestSuiteDTO saveTestSuite(TestSuite testSuite);

    List<TestSuiteDTO> saveTestSuites(List<TestSuite> testSuite);

    TestRun getOrCreateTestRunByExternalId(String externalProjectId, String testRunExternalId);

    ProjectDTO getProjectDTO(Long projectId) throws ProjectServiceException;

    Project getProject(Long projectId) throws ProjectServiceException;

}
