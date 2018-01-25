package org.tdb.model;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tdb.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DashboardRepositoryTests {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    DashboardRepository dashboardRepository;

    @Test
    public void getOwningAccount() {

        Account account = accountRepository.save(new Account("DashboardRepositoryTests Test Account"));
        Project project = projectRepository.save(new Project(account, "DashboardRepositoryTests Test Project"));
        Dashboard dashboard = dashboardRepository.save(new Dashboard("DashboardRepositoryTests Test Dashboard", account));

        Account owningAccount = dashboardRepository.getOwningAccount(dashboard.getId());

        Assert.assertThat(account.getId(), Matchers.is(owningAccount.getId()));

        // clean up
        dashboardRepository.delete(dashboard);
        projectRepository.delete(project);
        accountRepository.delete(account);

    }

}
