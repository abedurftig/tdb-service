package org.testdashboard.api.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.testdashboard.api.UploadApiDelegate;
import org.testdashboard.model.ResponseDTO;

/**
 * @author Arne
 * @since 26/11/2017
 */
@Component
public class UploadApiDelegateImpl implements UploadApiDelegate {

    @Override
    public ResponseEntity<ResponseDTO> upload(MultipartFile upfile, Long projectId, Long externalTestRunId) {

        String code = "200";

        String message = "Successfully uploaded - " +
                upfile.getOriginalFilename();

        ResponseDTO responseDTO = new ResponseDTO().message(message).code(code);

        return new ResponseEntity(responseDTO, new HttpHeaders(), HttpStatus.OK);

    }
}
