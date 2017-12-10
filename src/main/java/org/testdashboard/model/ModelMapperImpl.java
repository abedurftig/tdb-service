package org.testdashboard.model;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Handles the transformation from model DTO to model Entity and back.
 */
public class ModelMapperImpl {

    private ModelMapperImpl() {}

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

    /*
     * TestSuite
     */

    public static TestSuiteDTO getTestSuiteDTO(TestSuite testSuite) {
        return getPreConfiguredMapper().map(testSuite, TestSuiteDTO.class);
    }

    public static TestSuite getTestSuite(TestSuiteDTO testSuiteDTO) {
        return getPreConfiguredMapper().map(testSuiteDTO, TestSuite.class);
    }

    /*
     * TestRun
     */

    public static List<TestRunDTO> getTestRunDTOs(List<TestRun> testRuns) {

        Type targetListType = new TypeToken<List<TestRunDTO>>() {}.getType();
        List<TestRunDTO> testRunDTOs = getPreConfiguredMapper().map(testRuns, targetListType);
        return testRunDTOs;

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

}
