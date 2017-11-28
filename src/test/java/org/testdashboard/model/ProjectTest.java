package org.testdashboard.model;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class ProjectTest {

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

}
