package org.tdb.test;

import org.tdb.model.*;
import org.tdb.security.AccountSecurity;
import org.tdb.service.DashboardServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestHelper {

    private TestHelper() {}

    public static DashboardServiceImpl getMockedDashboardServiceImpl() {
        return new DashboardServiceImpl(
                getMockedDashboardRepository("Dashboard One"),
                getMockedProjectRepository(),
                getMockedAccountSecurity());
    }

    public static DashboardRepository getMockedDashboardRepository(String dashboardName) {

        DashboardRepository dashboardRepositoryMock = mock(DashboardRepository.class);

        Account fakeAccount = fakeAccount(1L, "Test Account");

        Optional<Dashboard> dashboardOptional = Optional.of(
                new Dashboard(dashboardName, fakeAccount));

        when(dashboardRepositoryMock.findByName(any())).thenReturn(Optional.empty());
        when(dashboardRepositoryMock.findByName(dashboardName)).thenReturn(dashboardOptional);
        when(dashboardRepositoryMock.save(any(Dashboard.class))).then(invocation -> invocation.getArgument(0));

        return dashboardRepositoryMock;

    }

    public static ProjectRepository getMockedProjectRepository() {

        ProjectRepository projectRepository = mock(ProjectRepository.class);

        Account fakeAccount = fakeAccount(1L, "Test Account");

        List<Project> projects = new ArrayList<>();
        projects.add(new ProjectBuilder(1L, "Project One").withAccount(fakeAccount).create());
        projects.add(new ProjectBuilder(2L, "Project Two").withAccount(fakeAccount).create());

        when(projectRepository.findByAccountId(any())).thenReturn(projects);
        when(projectRepository.save(any(Project.class))).then(invocation -> invocation.getArgument(0));

        return projectRepository;

    }

    public static AccountSecurity getMockedAccountSecurity() {

        AccountSecurity accountSecurityMock = mock(AccountSecurity.class);

        Account fakeAccount = fakeAccount(1L, "Test Account");
        when(accountSecurityMock.getCurrentAccount()).thenReturn(fakeAccount);
        when(accountSecurityMock.hasAccessToAccount(anyLong())).thenReturn(fakeAccount.getId() == 1L ? true : false);

        return accountSecurityMock;

    }

    public static Account fakeAccount(Long id, String name) {
        return new AccountBuilder().withId(id).withName(name).create();
    }

    public static User fakeUser(Long id, String email, String name) {
        return new UserBuilder().withId(id).withEmail(email).withName(name).create();
    }

}
