package org.testdashboard.model;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testdashboard.Application;

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

    @Test
    public void findByProjectIdAndExternalId() {

        Project project = projectRepository.save(new Project(null, "TestRunRepositoryTest Test Project"));
        testRunRepository.save(new TestRun(project, "TestRunRepositoryTest Test TestRun", "ExtId"));

        TestRun testRunByParentAndExternalId =
                testRunRepository.findByProjectIdAndExternalId(project.getId(), "ExtId").get();

        Assert.assertThat(testRunByParentAndExternalId, Matchers.notNullValue());
        Assert.assertThat(testRunByParentAndExternalId.getName(), Matchers.is("TestRunRepositoryTest Test TestRun"));

        testRunRepository.delete(testRunByParentAndExternalId);
        projectRepository.delete(project);

    }

    @Test
    public void findByProjectExternalIdAndExternalId() {

        Project project = projectRepository.save(new Project(null, "TestRunRepositoryTest Test Project"));
        testRunRepository.save(new TestRun(project, "TestRunRepositoryTest Test TestRun", "ExtId"));

        TestRun testRunByParentAndExternalId =
                testRunRepository.findByProjectExternalIdAndExternalId(project.getExternalId(), "ExtId").get();

        Assert.assertThat(testRunByParentAndExternalId, Matchers.notNullValue());
        Assert.assertThat(testRunByParentAndExternalId.getName(), Matchers.is("TestRunRepositoryTest Test TestRun"));

        testRunRepository.delete(testRunByParentAndExternalId);
        projectRepository.delete(project);

    }

}
