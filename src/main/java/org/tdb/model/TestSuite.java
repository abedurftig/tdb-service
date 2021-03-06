package org.tdb.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a set of test cases which belong to a single functional component.
 */
@Entity
@Table(name = "tdb_testsuite")
public class TestSuite extends BaseEntity {

    @ManyToOne
    private TestRun testRun;

    @OneToMany(mappedBy = "testSuite", cascade = CascadeType.ALL)
    private Set<TestCase> testCases = new HashSet<>();

    private int casesTotal;

    private int casesSkipped;

    private int casesWithError;

    private int casesWithFailure;

    private String packageName;

    private BigDecimal duration;

    public TestSuite() {}

    public TestSuite(TestRun testRun, String name, String packageName) {
        this(testRun, name, packageName, 0, 0, 0, 0, new BigDecimal(0));
    }

    public TestSuite(TestRun testRun, String name, String packageName, int casesTotal, int casesSkipped,
                     int casesWithError, int casesWithFailure, BigDecimal duration) {
        super(name);
        this.testRun = testRun;
        this.packageName = packageName;
        this.casesTotal = casesTotal;
        this.casesSkipped = casesSkipped;
        this.casesWithError = casesWithError;
        this.casesWithFailure = casesWithFailure;
        this.duration = duration;
    }

    public TestRun getTestRun() {
        return testRun;
    }

    public void setTestRun(TestRun testRun) {
        this.testRun = testRun;
    }

    public int getCasesTotal() {
        return casesTotal;
    }

    public int getCasesSkipped() {
        return casesSkipped;
    }

    public int getCasesWithError() {
        return casesWithError;
    }

    public int getCasesWithFailure() {
        return casesWithFailure;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public String getPackageName() {
        return packageName;
    }

    public Set<TestCase> getTestCases() {
        return Collections.unmodifiableSet(testCases);
    }

    public void addToTestCases(TestCase testCase) {
        testCases.add(testCase);
    }

}
