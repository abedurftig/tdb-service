package org.testdashboard.model;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;
import org.testdashboard.input.junit4.model.Testsuite;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Arne
 * @since 21/11/2017
 */
public class ModelMapperImpl {

    private ModelMapperImpl() {}

    /*
     * Account
     */

    public static Account getAccount(AccountDTO accountDTO) {
        return getPreconfiguredMapper().map(accountDTO, Account.class);
    }

    public static AccountDTO getAccountDTO(Account account) {
        return getPreconfiguredMapper().map(account, AccountDTO.class);
    }

    /*
     * Project
     */

    public static ProjectDTO getProjectDTO(Project project) {
        return getPreconfiguredMapper().map(project, ProjectDTO.class);
    }

    public static List<ProjectDTO> getProjectDTOs(List<Project> projects) {

        Type targetListType = new TypeToken<List<ProjectDTO>>() {}.getType();
        List<ProjectDTO> projectDTOs = getPreconfiguredMapper().map(projects, targetListType);
        return projectDTOs;

    }

    /*
     * TestSuite
     */

    public static TestSuiteDTO getTestSuiteDTO(TestSuite testSuite) {
        return getPreconfiguredMapper().map(testSuite, TestSuiteDTO.class);
    }

    public static TestSuite getTestSuite(TestSuiteDTO testSuiteDTO) {
        return getPreconfiguredMapper().map(testSuiteDTO, TestSuite.class);
    }

    /*
     * TestRun
     */

    public static List<TestRunDTO> getTestRunDTOs(List<TestRun> testRuns) {

        Type targetListType = new TypeToken<List<TestRunDTO>>() {}.getType();
        List<TestRunDTO> testRunDTOs = getPreconfiguredMapper().map(testRuns, targetListType);
        return testRunDTOs;

    }

    /*
     * Configuration
     */

    private static ModelMapper getPreconfiguredMapper() {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        return mapper;

    }

}
