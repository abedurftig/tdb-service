package org.testdashboard.model;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class TestRunTest {

    @Test
    public void constructorWithoutExternalIdParameterShouldMatchName() {

        TestRun testRun = new TestRun("Test TestRun");
        Assert.assertThat(testRun.getExternalId(), Matchers.is("Test TestRun"));

    }

    @Test
    public void constructorWithExternalIdParameterShouldMatchParameter() {

        TestRun testRun = new TestRun("Test TestRun", "External ID");
        Assert.assertThat(testRun.getExternalId(), Matchers.is("External ID"));

    }

}
