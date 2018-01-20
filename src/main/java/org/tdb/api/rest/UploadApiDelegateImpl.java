package org.tdb.api.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.tdb.input.InputParser;
import org.tdb.input.InputSource;
import org.tdb.input.junit4.TestsuiteInputParser;
import org.tdb.input.junit4.TestsuiteInputSource;
import org.tdb.input.junit4.TestsuitesInputParser;
import org.tdb.input.junit4.TestsuitesInputSource;
import org.tdb.model.ErrorDTO;
import org.tdb.model.TestRun;
import org.tdb.model.TestSuite;
import org.tdb.model.UploadSummaryDTO;
import org.tdb.service.ProjectService;

import java.io.IOException;
import java.util.List;

@RestController
public class UploadApiDelegateImpl implements UploadJunit4XmlApiDelegate, UploadJunit4XmlWrappedApiDelegate {

    private static Logger LOGGER = LoggerFactory.getLogger(UploadApiDelegateImpl.class);

    @Autowired
    private ProjectService projectService;

    @Override
    public ResponseEntity<UploadSummaryDTO> uploadJUnit4Xml(MultipartFile file, String externalProjectId, String externalTestRunId) {

        LOGGER.debug("uploadJUnit4Xml: begin upload");

        ResponseEntity responseEntity = uploadInternal(file, externalProjectId, externalTestRunId,
            new TestsuiteInputSource(), new TestsuiteInputParser());

        LOGGER.debug("uploadJUnit4Xml: upload complete");

        return responseEntity;

    }

    @Override
    public ResponseEntity<UploadSummaryDTO> uploadJUnit4XmlWrapped(MultipartFile file, String externalProjectId, String externalTestRunId) {

        LOGGER.debug("uploadJUnit4XmlWrapped: begin upload");

        ResponseEntity responseEntity = uploadInternal(file, externalProjectId, externalTestRunId,
                new TestsuitesInputSource(), new TestsuitesInputParser());

        LOGGER.debug("uploadJUnit4XmlWrapped: upload complete");

        return responseEntity;

    }

    private ResponseEntity<UploadSummaryDTO> uploadInternal(MultipartFile file, String externalProjectId,
                                                            String externalTestRunId, InputSource source, InputParser parser) {

        String code = "200";

        String message = "You uploaded a file with name " + file.getOriginalFilename() + " with externalProjectId = " +
                externalProjectId + " and externalTestRunId = " + externalTestRunId;

        TestRun testRun = projectService.getOrCreateTestRunByExternalId(
                externalProjectId, externalTestRunId);

        try {

            List<TestSuite> testSuites = source.buildTestSuites(parser.parseXML(file.getInputStream()), testRun);
            projectService.saveTestSuites(testSuites);

            UploadSummaryDTO uploadSummaryDTO =
                    new UploadSummaryDTO();

            uploadSummaryDTO.setTestRunId(testRun.getId());
            uploadSummaryDTO.setMessage(message);
            uploadSummaryDTO.setNumTestSuites(1);

            return new ResponseEntity<UploadSummaryDTO>(uploadSummaryDTO, new HttpHeaders(), HttpStatus.OK);

        } catch (IOException | InputParser.InputParseException e) {

            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.code(code).message(e.getMessage());
            return new ResponseEntity(errorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST);

        }

    }

}
