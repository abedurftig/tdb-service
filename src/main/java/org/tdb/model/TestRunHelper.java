package org.tdb.model;

/**
 * @author Arne
 * @since 08/01/2018
 */
public class TestRunHelper {

    private static TestRunHelper INSTANCE = null;

    private TestRunHelper() {
    }

    public static TestRunHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestRunHelper();
        }
        return INSTANCE;
    }



    public int getNumberOfPassedTestCases(TestRun testRun) {
        return testRun.getTestSuites().stream().mapToInt(testSuite ->
                testSuite.getCasesTotal() - testSuite.getCasesSkipped() -
                testSuite.getCasesWithError() - testSuite.getCasesWithFailure()).sum();
    }

    public int getNumberOfSkippedTestCases(TestRun testRun) {
        return testRun.getTestSuites().stream().mapToInt(testSuite ->
                testSuite.getCasesSkipped()).sum();
    }

    public int getNumberOfFailedTestCases(TestRun testRun) {
        return testRun.getTestSuites().stream().mapToInt(testSuite ->
                testSuite.getCasesWithError() + testSuite.getCasesWithFailure()).sum();
    }

    public int getNumberOfTestCases(TestRun testRun) {
        return testRun.getTestSuites().stream().mapToInt(testSuite ->
                testSuite.getCasesTotal() + testSuite.getCasesSkipped() +
                testSuite.getCasesWithError() + testSuite.getCasesWithFailure()).sum();
    }

}
