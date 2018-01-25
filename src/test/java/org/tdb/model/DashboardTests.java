package org.tdb.model;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class DashboardTests {

    @Test
    public void addProject() {

        Project project = new Project().withId(1L);
        Dashboard dashboard = new Dashboard();

        dashboard.addProject(project);

        Assert.assertThat("project should have been added to dashboard",
                dashboard.projectIds(), Matchers.contains(equalTo(1L)));

    }

    @Test
    public void removeProject() {

        Project project = new Project().withId(1L);
        Dashboard dashboard = new Dashboard();

        dashboard.addProject(project);

        Assert.assertThat("project should have been added to dashboard",
                dashboard.projectIds(), Matchers.contains(equalTo(1L)));

        dashboard.removeProject(project);

        Assert.assertThat("project should have been added to dashboard",
                dashboard.projectIds(), Matchers.hasSize(0));

    }

}
