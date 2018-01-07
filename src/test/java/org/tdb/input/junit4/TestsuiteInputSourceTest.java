package org.tdb.input.junit4;

import org.junit.Assert;
import org.junit.Test;
import org.tdb.input.junit4.model.Testsuite;
import org.tdb.model.TestCase;
import org.tdb.model.TestSuite;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import static org.junit.Assert.fail;

/**
 * @author Arne
 * @since 05/11/2017
 */
public class TestsuiteInputSourceTest {

    @Test
    public void testExtractPackageName() {

        String testName = "org.junitdashboard.xml.XMLInputSourceTest";
        Assert.assertEquals("extractPackageName() method",
                "org.junitdashboard.xml", new TestsuiteInputSource().extractPackageName(testName));

    }

    @Test
    public void testExtractTimestamp() {

        String pattern = "MM/dd/yyyy - HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String dateString = "11/05/2017 - 20:56:30";

        GregorianCalendar gc = new GregorianCalendar();
        XMLGregorianCalendar xmlDate = null;

        try {

            gc.setTime(sdf.parse(dateString));
            xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);

        } catch (ParseException e) {
            fail("extractTimestamp() method: could not parse example date");
        } catch (DatatypeConfigurationException e) {
            fail("extractTimestamp() method: could not instantiate XMLGregorianCalendar");
        }

        Assert.assertEquals("extractTimestamp() method",
                dateString, sdf.format(new TestsuiteInputSource().extractTimestamp(xmlDate)));

    }

    @Test
    public void testExtractMessageFromError() {

        Testsuite.Testcase.Error xmlError = new Testsuite.Testcase.Error();
        xmlError.setMessage("Error!");

        Testsuite.Testcase xmlTestCase = new Testsuite.Testcase();
        xmlTestCase.setError(xmlError);

        TestCase testCaseWithError = new TestsuiteInputSource().buildTestCase(new TestSuite(), xmlTestCase);

        Assert.assertEquals("message should match", "Error!", testCaseWithError.getMessage());
        Assert.assertEquals("should be error", true, testCaseWithError.isError());

    }

    @Test
    public void testExtractMessageFromFailure() {

        Testsuite.Testcase.Failure xmlFailure = new Testsuite.Testcase.Failure();
        xmlFailure.setMessage("Failure!");

        Testsuite.Testcase xmlTestCase = new Testsuite.Testcase();
        xmlTestCase.setFailure(xmlFailure);

        TestCase testCaseWithFailure = new TestsuiteInputSource().buildTestCase(new TestSuite(), xmlTestCase);

        Assert.assertEquals("message should match", "Failure!", testCaseWithFailure.getMessage());
        Assert.assertEquals("should be failure", true, testCaseWithFailure.isFailed());

    }

    @Test
    public void testExtractMessageFromSkipped() {

        Testsuite.Testcase.Skipped xmlSkipped = new Testsuite.Testcase.Skipped();
        xmlSkipped.setMessage("Skipped!");

        Testsuite.Testcase xmlTestCase = new Testsuite.Testcase();
        xmlTestCase.setSkipped(xmlSkipped);

        TestCase testCaseWithSkipped = new TestsuiteInputSource().buildTestCase(new TestSuite(), xmlTestCase);

        Assert.assertEquals("message should match", "Skipped!", testCaseWithSkipped.getMessage());
        Assert.assertEquals("should be skipped", true, testCaseWithSkipped.isSkipped());

    }

    @Test
    public void verifyBuildTestSuites() {

        Testsuite input = new Testsuite();
        input.setName("org.junitdashboard.xml.XMLInputParserTest");
        input.setTests(4);
        input.setErrors(1);
        input.setFailures(1);
        input.setSkipped(1);
        input.setTime(new BigDecimal(0.012));

        TestSuite testSuite = new TestsuiteInputSource().buildTestSuites(input, null).get(0);

        Assert.assertEquals("name should match", "org.junitdashboard.xml.XMLInputParserTest", testSuite.getName());
        Assert.assertEquals("casesTotal should match", 4, testSuite.getCasesTotal());
        Assert.assertEquals("casesSkipped should match", 1, testSuite.getCasesSkipped());
        Assert.assertEquals("casesWithFailure should match", 1, testSuite.getCasesWithFailure());
        Assert.assertEquals("casesWithError should match", 1, testSuite.getCasesWithError());
        Assert.assertEquals("package should match", "org.junitdashboard.xml", testSuite.getPackageName());
        Assert.assertEquals("duration should match", new BigDecimal(0.012), testSuite.getDuration());

    }

    @Test
    public void verifyBuildTestCaseWithError() {

        Testsuite.Testcase.Error xmlError = new Testsuite.Testcase.Error();
        xmlError.setMessage("Something went wrong!");
        xmlError.setType("java.lang.NullPointerException");

        Testsuite.Testcase xmlTestCase = new Testsuite.Testcase();
        xmlTestCase.setName("someTestMethodName");
        xmlTestCase.setTime(new BigDecimal(0.003));
        xmlTestCase.setError(xmlError);

        TestCase modelTestCase = new TestsuiteInputSource().buildTestCase(new TestSuite(), xmlTestCase);

        Assert.assertEquals("name should match", "someTestMethodName", modelTestCase.getName());
        Assert.assertEquals("duration should match", new BigDecimal(0.003), modelTestCase.getDuration());
        Assert.assertEquals("message should match", "Something went wrong!", modelTestCase.getMessage());
        Assert.assertEquals("should have error", true, modelTestCase.isError());

    }

    @Test
    public void verifyBuildTestCaseWithFailure() {

        Testsuite.Testcase.Failure xmlFailure = new Testsuite.Testcase.Failure();
        xmlFailure.setMessage("Something went wrong!");
        xmlFailure.setType("java.lang.IllegalArgumentException");

        Testsuite.Testcase xmlTestCase = new Testsuite.Testcase();
        xmlTestCase.setName("someTestMethodName");
        xmlTestCase.setTime(new BigDecimal(0.003));
        xmlTestCase.setFailure(xmlFailure);

        TestCase modelTestCase = new TestsuiteInputSource().buildTestCase(new TestSuite(), xmlTestCase);

        Assert.assertEquals("name should match", "someTestMethodName", modelTestCase.getName());
        Assert.assertEquals("duration should match", new BigDecimal(0.003), modelTestCase.getDuration());
        Assert.assertEquals("message should match", "Something went wrong!", modelTestCase.getMessage());
        Assert.assertEquals("should have failure", true, modelTestCase.isFailed());

    }

    @Test
    public void verifyBuildTestCaseWithSkipped() {

        Testsuite.Testcase.Skipped xmlSkipped = new Testsuite.Testcase.Skipped();
        xmlSkipped.setMessage("Cannot run this right now");

        Testsuite.Testcase xmlTestCase = new Testsuite.Testcase();
        xmlTestCase.setName("someTestMethodName");
        xmlTestCase.setTime(new BigDecimal(0.003));
        xmlTestCase.setSkipped(xmlSkipped);

        TestCase modelTestCase = new TestsuiteInputSource().buildTestCase(new TestSuite(), xmlTestCase);

        Assert.assertEquals("name should match", "someTestMethodName", modelTestCase.getName());
        Assert.assertEquals("duration should match", new BigDecimal(0.003), modelTestCase.getDuration());
        Assert.assertEquals("message should match", "Cannot run this right now", modelTestCase.getMessage());
        Assert.assertEquals("should be skipped", true, modelTestCase.isSkipped());

    }

}
