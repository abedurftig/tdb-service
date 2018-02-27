package org.tdb.service;

import org.tdb.model.TestRunDTO;

public interface TestRunService {

    /**
     * Get a test run by its id.
     *
     * @param id
     * @return the test run identified by the id
     * @throws TestRunServiceException if the user is not authorized
     */
    TestRunDTO getTestRunById(Long id) throws TestRunServiceException;

}
