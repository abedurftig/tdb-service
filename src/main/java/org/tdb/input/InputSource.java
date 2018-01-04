package org.tdb.input;

import org.tdb.model.TestRun;
import org.tdb.model.TestSuite;

import java.util.List;

public abstract class InputSource<I> {

    public abstract List<TestSuite> buildTestSuites(I input, TestRun testRun);

}
