package org.tdb.service;

import org.springframework.stereotype.Service;
import org.tdb.model.TestRunDTO;

@Service
public class TestRunServiceImpl implements TestRunService {

    @Override
    public TestRunDTO getTestRunByExternalId(String testRunExternalId) {
        return new TestRunDTO();
    }

}
