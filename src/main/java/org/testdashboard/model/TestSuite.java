package org.testdashboard.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Arne
 * @since 21/11/2017
 */
@Entity
@Table(name = "testsuite")
public class TestSuite extends BaseEntity {

    @ManyToOne
    private TestRun testRun;

    @OneToMany(mappedBy = "testSuite")
    private Set<TestCase> testCases = new HashSet<>();

    public TestSuite() {}

    public TestSuite(TestRun testRun, String name) {
        super(name);
        this.testRun = testRun;
    }

    public TestRun getTestRun() {
        return testRun;
    }

    public Set<TestCase> getTestCases() {
        return Collections.unmodifiableSet(testCases);
    }

    public void addToTestCases(TestCase testCase) {
        testCases.add(testCase);
    }

}
