package org.tdb.input;

import org.tdb.model.TestRun;
import org.tdb.model.TestSuite;

public abstract class InputSource<I> {

    public abstract TestSuite buildTestSuite(I input, TestRun testRun);

}
