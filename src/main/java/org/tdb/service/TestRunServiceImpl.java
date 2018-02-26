package org.tdb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdb.model.ModelMapperImpl;
import org.tdb.model.TestRun;
import org.tdb.model.TestRunDTO;
import org.tdb.model.TestRunRepository;
import org.tdb.security.AccountSecurity;

import java.util.Optional;

@Service
public class TestRunServiceImpl implements TestRunService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestRunServiceImpl.class);

    @Autowired
    AccountSecurity accountSecurity;

    private TestRunRepository testRunRepository;

    @Autowired
    TestRunServiceImpl(TestRunRepository testRunRepository) {
        this.testRunRepository = testRunRepository;
    }

    @Override
    public TestRunDTO getTestRunByExternalId(String testRunExternalId)
            throws TestRunServiceException {
        if (accountSecurity.hasAccessToTestRun(testRunExternalId)) {
            Optional<TestRun> testRunOptional = testRunRepository.findByExternalId(testRunExternalId);
            if (!testRunOptional.isPresent()) {
                throw TestRunServiceException.withDoesNotExist();
            }
            return ModelMapperImpl.getTestRunDTO(testRunOptional.get());
        } else {
            throw TestRunServiceException.withNotAuthorized();
        }
    }

}
