package org.testdashboard.model;

import org.h2.jdbc.JdbcSQLException;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.testdashboard.Application;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProjectTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Test
    public void constructorWithoutExternalIdParameterShouldMatchName() {

        Project project = new Project(null,"Test Project");
        Assert.assertThat(project.getExternalId(), Matchers.is("Test Project"));

    }

    @Test
    public void constructorWithExternalIdParameterShouldMatchParameter() {

        Project project = new Project(null,"Test Project", "External ID");
        Assert.assertThat(project.getExternalId(), Matchers.is("External ID"));

    }

    @Test
    public void externalIdUniqueConstraint() {

        Account account = accountRepository.save(new Account());
        Project project = projectRepository.save(new Project(account, "Test One", "123AB"));

        try {
            projectRepository.save(new Project(account, "Test Two", "123AB"));
            fail("Should throw unique constraint exception");
        } catch (DataIntegrityViolationException e) {}

        projectRepository.delete(project);
        accountRepository.delete(account);

    }

}
