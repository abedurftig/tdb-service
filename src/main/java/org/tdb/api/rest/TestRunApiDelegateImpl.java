package org.tdb.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.tdb.model.TestRunDTO;
import org.tdb.service.TestRunService;

@Component
public class TestRunApiDelegateImpl implements TestRunApiDelegate {

    TestRunService testRunService;

    @Autowired
    public TestRunApiDelegateImpl(TestRunService testRunService) {
        this.testRunService = testRunService;
    }

//    @Override
//    public ResponseEntity<TestRunDTO> getTestRun(Long testRunExternalId) {
//        return null;
//    }
}
