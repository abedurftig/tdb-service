package org.tdb.model;

import org.assertj.core.api.Condition;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ModelMapperTest {

    @Test
    public void accountModelToDto() {

        // given
        Account account = new Account("Test Account");
        account.setId(123L);

        // when
        AccountDTO dto = ModelMapperImpl.getAccountDTO(account);

        // then
        Assert.assertThat(dto.getName(), Matchers.is("Test Account"));
        Assert.assertThat(dto.getId(), Matchers.is(123L));

    }

    @Test
    public void accountDtoToModel() {

        // given
        AccountDTO accountDto = new AccountDTO();
        accountDto.setId(123L);
        accountDto.setName("Test Account");

        // when
        Account account = ModelMapperImpl.getAccount(accountDto);

        // then
        Assert.assertThat(account.getName(), Matchers.is("Test Account"));
        Assert.assertThat(account.getId(), Matchers.is(123L));

    }

    @Test
    public void projectToProjectDto() {

        // given
        Account account = new Account("Test Account");
        account.setId(123L);

        Project project = new Project(account, "Test Project");
        project.setId(123L);

        // when
        ProjectDTO dto = ModelMapperImpl.getProjectDTO(project);

        // then
        Assert.assertThat(dto.getName(), Matchers.is("Test Project"));
        Assert.assertThat(dto.getId(), Matchers.is(123L));
        Assert.assertThat(dto.getAccountId(), Matchers.is(123L));

    }

    @Test
    public void projectListToProjectDtoList() {

        Account account = new Account("Test Account");

        Project projectOne = new Project(account, "Project One");
        Project projectTwo = new Project(account, "Project Two");

        List<Project> projects = new ArrayList<>();
        projects.add(projectOne);
        projects.add(projectTwo);

        List<ProjectDTO> projectDtos = ModelMapperImpl.getProjectDTOs(projects);
        Assert.assertThat(projectDtos.size(), Matchers.is(2));

    }

    @Test
    public void dashboardToDashboardDTO() {

        Account account = new Account("Account One");
        Dashboard dashboard = new Dashboard("Dashboard One", account);
        dashboard.addProject(new Project(account, "Project One").withId(1L));
        dashboard.addProject(new Project(account, "Project Two").withId(2L));
        dashboard.addProject(new Project(account, "Project Three").withId(3L));

        DashboardDTO dashboardDTO = ModelMapperImpl.getDashboardDTO(dashboard);

        assertThat(dashboardDTO.getItems()).hasSize(3).contains(
                new DashboardItemDTO().projectId(1L).name("Project One"),
                new DashboardItemDTO().projectId(2L).name("Project Two"),
                new DashboardItemDTO().projectId(3L).name("Project Three"));
        assertThat(dashboardDTO.getName()).isEqualTo("Dashboard One");

    }

    @Test
    public void testRunToTestRunDTO() {

        TestCase testCaseFailed = new TestCase(null, "failed", true, false, false, "", "", BigDecimal.ZERO);
        TestCase testCaseError = new TestCase(null, "error", false, false, true, "", "", BigDecimal.ZERO);
        TestCase testCasePassed = new TestCase(null, "passed", false, false, false, "", "", BigDecimal.ZERO);
        TestCase testCaseSkipped = new TestCase(null, "skipped", false, true, false, "", "", BigDecimal.ZERO);

        TestSuite testSuite = new TestSuite();
        testSuite.addToTestCases(testCaseError);
        testSuite.addToTestCases(testCaseFailed);
        testSuite.addToTestCases(testCasePassed);
        testSuite.addToTestCases(testCaseSkipped);

        TestRun testRun = new TestRun(null, "Test Run", "Test Run (Ext)");
        testRun.addToTestSuites(testSuite);

        TestRunDTO testRunDto = ModelMapperImpl.getTestRunDTO(testRun);

        assertThat(testRunDto.getExternalId()).isEqualTo("Test Run (Ext)");
        assertThat(testRunDto.getName()).isEqualTo("Test Run");

        assertThat(testRunDto.getTestSuites()).hasSize(1)
                .element(0).isInstanceOf(TestSuiteDTO.class);

        TestSuiteDTO testSuiteDto = testRunDto.getTestSuites().get(0);

        assertThat(testSuiteDto.getTestCases()).hasSize(4)
                .haveExactly(1, new Condition<TestCaseDTO>(testCaseDTO -> testCaseDTO.getFailed(), "test case failed"))
                .haveExactly(1, new Condition<TestCaseDTO>(testCaseDTO -> testCaseDTO.getSkipped(), "test case skipped"))
                .haveExactly(1, new Condition<TestCaseDTO>(testCaseDTO -> testCaseDTO.getError(), "test case error"))
                .haveExactly(1, new Condition<TestCaseDTO>(testCaseDTO ->
                        !testCaseDTO.getError() && !testCaseDTO.getFailed() && !testCaseDTO.getSkipped(), "test case passed")
                );

        assertThat(testRunDto.getFailedTestCases()).isNotNull().isNotEmpty().hasSize(2);
        assertThat(testRunDto.getSkippedTestCases()).isNotNull().isNotEmpty().hasSize(1);

    }

}
