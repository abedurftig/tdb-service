package org.tdb.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Model Entity for a single test.
 */
@Entity
@Table(name = "tdb_testcase")
public class TestCase extends BaseEntity {

    @ManyToOne
    private TestSuite testSuite;

    private boolean failed;

    private boolean error;

    private boolean skipped;

    private String message;

    private BigDecimal duration;

    public TestCase() {}

    public TestCase(TestSuite testSuite, String name) {
        this(testSuite, name, false, false, false, "", new BigDecimal(0));
    }

    public TestCase(TestSuite testSuite, String name, boolean failed,
                    boolean skipped, boolean error, String message, BigDecimal duration) {
        super(name);
        this.testSuite = testSuite;
        this.failed = failed;
        this.skipped = skipped;
        this.error = error;
        this.message = message;
        this.duration = duration;
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

    public BigDecimal getDuration() {
        return duration;
    }

}
