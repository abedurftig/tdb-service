package org.tdb.input.junit4;

import org.tdb.input.InputSource;
import org.tdb.input.junit4.model.Testsuite;
import org.tdb.input.junit4.model.Testsuites;
import org.tdb.model.TestRun;
import org.tdb.model.TestSuite;

import java.util.ArrayList;
import java.util.List;

public class TestsuitesInputSource extends InputSource<Testsuites> {

    @Override
    public List<TestSuite> buildTestSuites(Testsuites input, TestRun testRun) {

        TestsuiteInputSource testsuiteInputSource =
                new TestsuiteInputSource();

        List<TestSuite> testSuites = new ArrayList<>();
        for (Testsuite ts : input.getTestsuite()) {
            testSuites.addAll(testsuiteInputSource.buildTestSuites(ts, testRun));
        }

        return testSuites;

    }

}
