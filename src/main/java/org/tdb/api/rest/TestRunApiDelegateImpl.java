package org.tdb.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.tdb.model.TestRunDTO;
import org.tdb.service.TestRunService;
import org.tdb.service.TestRunServiceException;

@Component
public class TestRunApiDelegateImpl implements TestRunApiDelegate {

    private TestRunService testRunService;

    @Autowired
    public TestRunApiDelegateImpl(TestRunService testRunService) {
        this.testRunService = testRunService;
    }

    @Override
    public ResponseEntity<TestRunDTO> getTestRun(String testRunExternalId) {

        try {
            return new ResponseEntity<>(
                    testRunService.getTestRunByExternalId(testRunExternalId),
                    HttpStatus.OK);
        } catch (TestRunServiceException e) {
            return ErrorResponseHelper.resolveFromServiceException(e);
        }

    }

}
