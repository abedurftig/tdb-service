package org.testdashboard.input;

import org.testdashboard.model.TestRun;
import org.testdashboard.model.TestSuite;

public abstract class InputSource<I> {

    public abstract TestSuite buildTestSuite(I input, TestRun testRun);

}
