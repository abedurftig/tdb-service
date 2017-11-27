package org.testdashboard.api.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.testdashboard.api.UploadApiDelegate;
import org.testdashboard.model.ResponseDTO;

/**
 * @author Arne
 * @since 26/11/2017
 */
@RestController
public class UploadApiDelegateImpl implements UploadApiDelegate {

    @Override
    public ResponseEntity<ResponseDTO> upload(MultipartFile file, String externalProjectId, String externalTestRunId) {

        String code = "200";

        String message = "You uploaded a file with name " + file.getOriginalFilename() + " with externalProjectId = " +
                externalProjectId + " and externalTestRunId = " + externalTestRunId;

        ResponseDTO responseDTO = new ResponseDTO().message(message).code(code);

        return new ResponseEntity(responseDTO, new HttpHeaders(), HttpStatus.OK);

    }
}
