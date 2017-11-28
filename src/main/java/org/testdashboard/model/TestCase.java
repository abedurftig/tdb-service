package org.testdashboard.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Arne
 * @since 21/11/2017
 */
@Entity
@Table(name = "testcase")
public class TestCase extends BaseEntity {

    @ManyToOne
    private TestSuite testSuite;

    private boolean failed;

    private boolean skipped;

    private boolean error;

    private String message;

    public TestCase() {}

    public TestCase(TestSuite testSuite, String name) {
        this(testSuite, name, false, false, false, "");
    }

    public TestCase(TestSuite testSuite, String name, boolean failed, boolean skipped, boolean error, String message) {
        super(name);
        this.testSuite = testSuite;
        this.failed = failed;
        this.skipped = skipped;
        this.error = error;
        this.message = message;
    }

    public TestSuite getTestSuite() {
        return testSuite;
    }

    public boolean isFailed() {
        return failed;
    }

    public boolean isSkipped() {
        return skipped;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

}
