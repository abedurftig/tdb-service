package org.tdb.model;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * @author Arne
 * @since 08/01/2018
 */
public class TestRunHelperTest {

    @Test
    public void getNumberOfPassedTestCases() {

        TestSuite testSuiteOne = mockTestSuite(10, 4, 1, 0);
        TestSuite testSuiteTwo = mockTestSuite(23, 4, 2, 1);

        TestRun testRun = new TestRun();
        testRun.addToTestSuites(testSuiteOne);
        testRun.addToTestSuites(testSuiteTwo);

        int passedTestCases = TestRunHelper.getInstance().getNumberOfPassedTestCases(testRun);
        Assert.assertEquals("number of passed test cases should match", 21, passedTestCases);

    }

    @Test
    public void getNumberOfSkippedTestCases() {

        TestSuite testSuiteOne = mockTestSuite(10, 4, 1, 0);
        TestSuite testSuiteTwo = mockTestSuite(23, 4, 2, 1);

        TestRun testRun = new TestRun();
        testRun.addToTestSuites(testSuiteOne);
        testRun.addToTestSuites(testSuiteTwo);

        int skippedTestCases = TestRunHelper.getInstance().getNumberOfSkippedTestCases(testRun);
        Assert.assertEquals("number of skipped test cases should match", 8, skippedTestCases);

    }

    @Test
    public void getNumberOfFailedTestCases() {

        TestSuite testSuiteOne = mockTestSuite(10, 4, 1, 0);
        TestSuite testSuiteTwo = mockTestSuite(23, 4, 2, 1);

        TestRun testRun = new TestRun();
        testRun.addToTestSuites(testSuiteOne);
        testRun.addToTestSuites(testSuiteTwo);

        int failedTestCases = TestRunHelper.getInstance().getNumberOfFailedTestCases(testRun);
        Assert.assertEquals("number of test cases with error or failure should match", 4, failedTestCases);

    }

    @Test
    public void getNumberOfTestCases() {

        TestSuite testSuiteOne = mockTestSuite(10, 4, 1, 0);
        TestSuite testSuiteTwo = mockTestSuite(23, 4, 2, 1);

        TestRun testRun = new TestRun();
        testRun.addToTestSuites(testSuiteOne);
        testRun.addToTestSuites(testSuiteTwo);

        int testCases = TestRunHelper.getInstance().getNumberOfTestCases(testRun);
        Assert.assertEquals("number of test cases should match", 45, testCases);

    }

    public TestSuite mockTestSuite(int total, int skipped, int error, int failure) {
        TestSuite mockSuite = mock(TestSuite.class);
        when(mockSuite.getCasesTotal()).thenReturn(total);
        when(mockSuite.getCasesSkipped()).thenReturn(skipped);
        when(mockSuite.getCasesWithError()).thenReturn(error);
        when(mockSuite.getCasesWithFailure()).thenReturn(failure);
        return mockSuite;
    }
}