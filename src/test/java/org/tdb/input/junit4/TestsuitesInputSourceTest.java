package org.tdb.input.junit4;

import org.junit.Assert;
import org.junit.Test;
//import org.tdb.input.junit4.model.Testsuite;
import org.tdb.input.junit4.model.Testsuites;
import org.tdb.input.junit4.model.Testsuites.Testsuite;
import org.tdb.model.TestSuite;

import java.math.BigDecimal;

/**
 * @author Arne
 * @since 07/01/2018
 */
public class TestsuitesInputSourceTest {

    @Test
    public void verifyBuildTestSuites() {

        Testsuites input = new Testsuites();

        Testsuite ts1 = new Testsuite();

        ts1.setName("org.junitdashboard.xml.XMLInputParserTest");
        ts1.setTests(4);
        ts1.setErrors(1);
        ts1.setFailures(1);
        ts1.setSkipped(1);
        ts1.setTime(new BigDecimal(0.012));

        input.getTestsuite().add(ts1);

        TestSuite testSuite = new TestsuitesInputSource().buildTestSuites(input, null).get(0);

        Assert.assertEquals("name should match", "org.junitdashboard.xml.XMLInputParserTest", testSuite.getName());
        Assert.assertEquals("casesTotal should match", 4, testSuite.getCasesTotal());
        Assert.assertEquals("casesSkipped should match", 1, testSuite.getCasesSkipped());
        Assert.assertEquals("casesWithFailure should match", 1, testSuite.getCasesWithFailure());
        Assert.assertEquals("casesWithError should match", 1, testSuite.getCasesWithError());
        Assert.assertEquals("package should match", "org.junitdashboard.xml", testSuite.getPackageName());
        Assert.assertEquals("duration should match", new BigDecimal(0.012), testSuite.getDuration());

    }

}
