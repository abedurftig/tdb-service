package org.tdb.model;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class DashboardTests {

    @Test
    public void addProject() {

        Project project = new Project(null, "Project One").withId(1L);
        Dashboard dashboard = new Dashboard();

        dashboard.addProject(project);

        Assert.assertThat("project should have been added to dashboard",
                dashboard.getDashboardItems(), Matchers.contains(equalTo(new Dashboard.DashboardItem(1L, "Project One"))));

    }

    @Test
    public void removeProject() {

        Project project = new Project(null, "Project One").withId(1L);
        Dashboard dashboard = new Dashboard();

        dashboard.addProject(project);

        Assert.assertThat("project should have been added to dashboard",
                dashboard.getDashboardItems(), Matchers.contains(equalTo(new Dashboard.DashboardItem(1L, "Project One"))));

        dashboard.removeProject(project);

        Assert.assertThat("project should have been added to dashboard",
                dashboard.getDashboardItems(), Matchers.hasSize(0));

    }

}
