package org.testdashboard.junit4;

import org.testdashboard.junit4.model.Testsuite;
import org.testdashboard.input.InputSource;
import org.testdashboard.model.TestCase;
import org.testdashboard.model.TestSuite;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;

/**
 * @author Arne
 * @since 05/11/2017
 */
public class XMLInputSource extends InputSource<Testsuite> {

    public TestSuite buildTestSuite(Testsuite input) {

        TestSuite suite = new TestSuite(null, input.getName());

        for (Testsuite.Testcase testcase : input.getTestcase()) {
            suite.addToTestCases(buildTestCase(suite, testcase));
        }

        return suite;

    }

    protected TestCase buildTestCase(TestSuite testSuite, Testsuite.Testcase input) {

        String message = input.getFailure() != null ? input.getFailure().getMessage() : "";

        TestCase testCase = new TestCase(
                testSuite,
                input.getName(),
                input.getFailure() != null,
                input.getSkipped() != null,
                input.getError() != null,
                 message);

        return testCase;

    }

    protected String extractFailureOrErrorMessage(Testsuite.Testcase input) {

        if (input.getError() != null) {
            return input.getError().getMessage();
        } else if (input.getFailure() != null) {
            return input.getFailure().getMessage();
        } else {
            return null;
        }

    }

    protected String extractPackageName(String xmlInput) {
        return xmlInput.substring(0, xmlInput.lastIndexOf("."));
    }

    protected Date extractTimestamp(XMLGregorianCalendar xmlInput) {
        return xmlInput.toGregorianCalendar().getTime();
    }

}
