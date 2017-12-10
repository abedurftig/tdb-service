package org.testdashboard.input.junit4;

import org.testdashboard.input.InputSource;
import org.testdashboard.input.junit4.model.Testsuite;
import org.testdashboard.model.TestCase;
import org.testdashboard.model.TestRun;
import org.testdashboard.model.TestSuite;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Arne
 * @since 05/11/2017
 */
public class XMLInputSource extends InputSource<Testsuite> {

    public TestSuite buildTestSuite(Testsuite input, TestRun testRun) {

        TestSuite suite = new TestSuite(testRun, input.getName(),
                extractPackageName(input.getName()), input.getTests(),
                input.getSkipped(), input.getErrors(), input.getFailures(), input.getTime());

        for (Testsuite.Testcase testcase : input.getTestcase()) {
            suite.addToTestCases(buildTestCase(suite, testcase));
        }

        return suite;

    }

    protected TestCase buildTestCase(TestSuite testSuite, Testsuite.Testcase input) {

        String message = extractMessage(input);

        TestCase testCase = new TestCase(
                testSuite,
                input.getName(),
                input.getFailure() != null,
                input.getSkipped() != null,
                input.getError() != null,
                 message,
                input.getTime());

        return testCase;

    }

    protected String extractMessage(Testsuite.Testcase input) {

        return input.getFailure() != null ? input.getFailure().getMessage() :
               input.getError() != null ? input.getError().getMessage() :
               input.getSkipped() != null ? input.getSkipped().getMessage() :
               "";

    }

    protected String extractPackageName(String xmlInput) {
        return xmlInput.substring(0, xmlInput.lastIndexOf("."));
    }

    protected Date extractTimestamp(XMLGregorianCalendar xmlInput) {
        return xmlInput.toGregorianCalendar().getTime();
    }

}
