package org.tdb.model;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
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

        assertThat(dashboardDTO.getProjectIds()).hasSize(3).contains(1L, 2L, 3L);
        assertThat(dashboardDTO.getName()).isEqualTo("Dashboard One");

    }

}
