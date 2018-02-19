package org.tdb.api.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.tdb.model.ResponseDTO;

@Component
public class StatusApiDelegateImpl implements StatusApiDelegate {

    @Override
    public ResponseEntity<ResponseDTO> ping() {
        ResponseDTO responseDTO = new ResponseDTO()
                .message("Server is up!")
                .code(HttpStatus.OK.name());
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
