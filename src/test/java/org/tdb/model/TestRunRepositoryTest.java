package org.tdb.model;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.tdb.Application;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Arne
 * @since 28/11/2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestRunRepositoryTest {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TestRunRepository testRunRepository;

    @After
    public void clearRepositories() {
        testRunRepository.deleteAll();
        projectRepository.deleteAll();
    }

    @Test
    public void findByProjectIdAndExternalId() {

        Project project = projectRepository.save(new Project(null, "TestRunRepositoryTest Test Project"));
        testRunRepository.save(new TestRun(project, "TestRunRepositoryTest Test TestRun", "ExtId"));

        TestRun testRunByParentAndExternalId =
                testRunRepository.findByProjectIdAndExternalId(project.getId(), "ExtId").get();

        Assert.assertThat(testRunByParentAndExternalId, Matchers.notNullValue());
        Assert.assertThat(testRunByParentAndExternalId.getName(), Matchers.is("TestRunRepositoryTest Test TestRun"));

    }

    @Test
    public void findByProjectExternalIdAndExternalId() {

        Project project = projectRepository.save(new Project(null, "TestRunRepositoryTest Test Project"));
        testRunRepository.save(new TestRun(project, "TestRunRepositoryTest Test TestRun", "ExtId"));

        TestRun testRunByParentAndExternalId =
                testRunRepository.findByProjectExternalIdAndExternalId(project.getExternalId(), "ExtId").get();

        Assert.assertThat(testRunByParentAndExternalId, Matchers.notNullValue());
        Assert.assertThat(testRunByParentAndExternalId.getName(), Matchers.is("TestRunRepositoryTest Test TestRun"));

    }

    @Test
    public void findByExternalId() {

        Project project = projectRepository.save(new Project(null, "TestRunRepositoryTest Test Project"));
        testRunRepository.save(new TestRun(project, "TestRunRepositoryTest Test TestRun", "ExtId"));

        TestRun testRunByExternalId =
                testRunRepository.findByExternalId("ExtId").get();

        Assert.assertThat(testRunByExternalId, Matchers.notNullValue());
        Assert.assertThat(testRunByExternalId.getName(), Matchers.is("TestRunRepositoryTest Test TestRun"));

    }

    @Test
    public void externalIdIsUnique() {

        Project project = projectRepository.save(new Project(null, "TestRunRepositoryTest Test Project"));
        TestRun testRunOne = testRunRepository.save(new TestRun(project, "TestRunRepositoryTest Test TestRun 1", "ExtId"));

        try {
            TestRun testRunTwo = testRunRepository.save(new TestRun(project, "TestRunRepositoryTest Test TestRun 2", "ExtId"));
        } catch (Exception e) {
            assertThat(e).isInstanceOf(DataIntegrityViolationException.class);
        }

    }

}
