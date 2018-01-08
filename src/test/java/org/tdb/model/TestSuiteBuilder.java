package org.tdb.model;

import java.math.BigDecimal;

/**
 * @author Arne
 * @since 08/01/2018
 */
public class TestSuiteBuilder extends DataBuilder<TestSuite> {

    private int casesSkipped = 0;
    private int casesWithError = 0;
    private int casesWithFailure = 0;
    private int casesTotal = 0;

    public TestSuiteBuilder withCasesSkipped(int casesSkipped) {
        this.casesSkipped = casesSkipped;
        return this;
    }

    public TestSuiteBuilder withCasesWithError(int casesWithError) {
        this.casesWithError = casesWithError;
        return this;
    }

    public TestSuiteBuilder withCasesWithFailure(int casesWithFailure) {
        this.casesWithFailure = casesWithFailure;
        return this;
    }

    public TestSuiteBuilder withCasesTotal(int casesTotal) {
        this.casesTotal = casesTotal;
        return this;
    }

    @Override
    public TestSuite create() {
        TestSuite testSuite = new TestSuite(null, "name_TODO",
                "packageName_TODO", casesTotal, casesSkipped,
                 casesWithError, casesWithFailure, new BigDecimal(0));
        return testSuite;
    }
}
