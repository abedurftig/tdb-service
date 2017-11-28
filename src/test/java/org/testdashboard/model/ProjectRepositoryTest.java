package org.testdashboard.model;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testdashboard.Application;

import java.util.List;

/**
 * @author Arne
 * @since 28/11/2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProjectRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Test
    public void findByAccountId() {

        Account account = accountRepository.save(new Account("ProjectRepositoryTest Test Account"));
        Project project = projectRepository.save(new Project(account, "ProjectRepositoryTest Test Project"));

        List<Project> accountProjects = projectRepository.findByAccountId(account.getId());

        Assert.assertThat(accountProjects.size(), Matchers.is(1));
        Assert.assertThat(accountProjects.get(0).getId(), Matchers.is(project.getId()));

        // clean up
        projectRepository.delete(project);
        accountRepository.delete(account);

    }

    @Test
    public void findByExternalId() {

        Account account = accountRepository.save(new Account("ProjectRepositoryTest Test Account"));
        Project project = projectRepository.save(new Project(account, "ProjectRepositoryTest Test Project"));

        Project projectByExternalId = projectRepository.findByExternalId("ProjectRepositoryTest Test Project").get();

        Assert.assertThat(projectByExternalId.getExternalId(), Matchers.is(project.getExternalId()));

        // clean up
        projectRepository.delete(project);
        accountRepository.delete(account);

    }

}
