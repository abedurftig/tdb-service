package org.tdb.model;

import java.util.HashSet;
import java.util.Set;

public class TestRunBuilder extends DataBuilder<TestRun> {

    private Set<TestSuite> testSuites = new HashSet<>();

    public TestRunBuilder addTestSuite(TestSuite testSuite) {
        testSuites.add(testSuite);
        return this;
    }

    @Override
    public TestRun create() {
        TestRun testRun = new TestRun();
        testSuites.forEach(testSuite -> testRun.addToTestSuites(testSuite));
        return testRun;
    }
}
