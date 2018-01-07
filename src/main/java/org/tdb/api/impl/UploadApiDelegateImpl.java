package org.tdb.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.tdb.api.UploadJunit4XmlApiDelegate;
import org.tdb.input.InputParser;
import org.tdb.input.junit4.TestsuiteInputParser;
import org.tdb.input.junit4.TestsuiteInputSource;
import org.tdb.model.*;
import org.tdb.service.ProjectService;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Arne
 * @since 26/11/2017
 */
@RestController
public class UploadApiDelegateImpl implements UploadJunit4XmlApiDelegate {

    @Autowired
    private ProjectService projectService;

    @Override
    public ResponseEntity<TestSuiteDTO> uploadJUnit4Xml(MultipartFile file, String externalProjectId, String externalTestRunId) {

        String code = "200";

        String message = "You uploaded a file with name " + file.getOriginalFilename() + " with externalProjectId = " +
                externalProjectId + " and externalTestRunId = " + externalTestRunId;

        TestRun testRun = projectService.getOrCreateTestRunByExternalId(
                externalProjectId, externalTestRunId);


        try {

            TestSuiteDTO testSuiteDTO = projectService.saveTestSuite(
                    transformTestSuiteXML(file.getInputStream(), testRun));

            return new ResponseEntity(testSuiteDTO, new HttpHeaders(), HttpStatus.OK);
        } catch (IOException ioe) {
            return new ResponseEntity(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        } catch (InputParser.InputParseException ipe) {
            return new ResponseEntity(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

    }

    public TestSuite transformTestSuiteXML(InputStream inputStream, TestRun testRun) throws InputParser.InputParseException {

        TestSuite testSuite = new TestsuiteInputSource().buildTestSuites(
                new TestsuiteInputParser().parseXML(inputStream), testRun).get(0);
        return testSuite;

    }

}
