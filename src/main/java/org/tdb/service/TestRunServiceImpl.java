package org.tdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdb.model.TestRunDTO;
import org.tdb.model.TestRunRepository;

@Service
public class TestRunServiceImpl implements TestRunService {

    private TestRunRepository testRunRepository;

    @Autowired
    TestRunServiceImpl(TestRunRepository testRunRepository) {
        this.testRunRepository = testRunRepository;
    }

    @Override
    public TestRunDTO getTestRunByExternalId(String testRunExternalId) {
        return new TestRunDTO();
    }

}
