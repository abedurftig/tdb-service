package org.tdb.service;

import org.tdb.model.TestRunDTO;

public interface TestRunService {

    /**
     * Get a test run by its external id.
     *
     * @param testRunExternalId
     * @return the test run identified by the external id
     */
    TestRunDTO getTestRunByExternalId(String testRunExternalId);

}
