package org.tdb.model;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

/**
 * Handles the transformation from model DTO to model Entity and back.
 */
public class ModelMapperImpl {

    private ModelMapperImpl() {}

    /**
     * User
     */

    public static User getUser(UserDTO userDTO) {
        return getPreConfiguredMapper().map(userDTO, User.class);
    }

    public static UserDTO getUserDTO(User user) {
        return getPreConfiguredMapper().map(user, UserDTO.class);
    }

    /*
     * Account
     */

    public static Account getAccount(AccountDTO accountDTO) {
        return getPreConfiguredMapper().map(accountDTO, Account.class);
    }

    public static AccountDTO getAccountDTO(Account account) {
        return getPreConfiguredMapper().map(account, AccountDTO.class);
    }

    /*
     * Project
     */

    public static ProjectDTO getProjectDTO(Project project) {
        return getPreConfiguredMapper().map(project, ProjectDTO.class);
    }

    public static List<ProjectDTO> getProjectDTOs(List<Project> projects) {

        Type targetListType = new TypeToken<List<ProjectDTO>>() {}.getType();
        List<ProjectDTO> projectDTOs = getPreConfiguredMapper().map(projects, targetListType);
        return projectDTOs;

    }

    public static ProjectSummaryDTO getProjectSummaryDTO(Project project) {
        ProjectSummaryDTO projectSummaryDTO = new ProjectSummaryDTO();
        projectSummaryDTO.setAccountId(project.getAccount().getId());
        projectSummaryDTO.setId(project.getId());
        projectSummaryDTO.setName(project.getName());
        projectSummaryDTO.setExternalId(project.getExternalId());
        projectSummaryDTO.setNumTestRuns(project.getTestRuns().size());
        return projectSummaryDTO;
    }

    /*
     * TestSuite
     */

    public static TestSuiteDTO getTestSuiteDTO(TestSuite testSuite) {
        return getPreConfiguredMapper().map(testSuite, TestSuiteDTO.class);
    }

    public static TestSuite getTestSuite(TestSuiteDTO testSuiteDTO) {
        return getPreConfiguredMapper().map(testSuiteDTO, TestSuite.class);
    }

    public static List<TestSuiteDTO> getTestSuiteDTOs(List<TestSuite> testSuites) {

        Type targetListType = new TypeToken<List<TestSuiteDTO>>() {}.getType();
        List<TestSuiteDTO> testSuiteDTOs = getPreConfiguredMapper().map(testSuites, targetListType);
        return testSuiteDTOs;

    }

    /*
     * TestRun
     */

    public static List<TestRunDTO> getTestRunDTOs(List<TestRun> testRuns) {

        Type targetListType = new TypeToken<List<TestRunDTO>>() {}.getType();
        List<TestRunDTO> testRunDTOs = getPreConfiguredMapper().map(testRuns, targetListType);
        return testRunDTOs;

    }

    public static TestRunSummaryDTO getTestRunSummaryDTO(TestRun testRun) {

        TestRunHelper testRunHelper = TestRunHelper.getInstance();

        TestRunSummaryDTO testRunSummaryDTO = new TestRunSummaryDTO();
        testRunSummaryDTO.setExternalId(testRun.getExternalId());
        testRunSummaryDTO.setId(testRun.getId());
        testRunSummaryDTO.setName(testRun.getName());
        testRunSummaryDTO.setNumTestSuites(testRun.getTestSuites().size());
        testRunSummaryDTO.setNumPassed(testRunHelper.getNumberOfPassedTestCases(testRun));
        testRunSummaryDTO.setNumSkipped(testRunHelper.getNumberOfSkippedTestCases(testRun));
        testRunSummaryDTO.setNumFailed(testRunHelper.getNumberOfFailedTestCases(testRun));
        testRunSummaryDTO.setNumTotal(testRunHelper.getNumberOfTestCases(testRun));
        testRunSummaryDTO.setProjectId(testRun.getProject().getId());

        return testRunSummaryDTO;

    }

    /*
     * Configuration
     */

    private static ModelMapper getPreConfiguredMapper() {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        return mapper;

    }

    public static DashboardDTO getDashboardDTO(Dashboard dashboard) {
        DashboardDTO dashboardDTO = getPreConfiguredMapper().map(dashboard, DashboardDTO.class);
        dashboardDTO.setProjectIds(dashboard.projectIds());
        return dashboardDTO;
    }
}
