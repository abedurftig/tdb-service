package org.tdb.api.rest;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.tdb.model.ResponseDTO;

import static org.junit.Assert.assertEquals;

public class StatusApiDelegateImplTests {

    @Test
    public void ping() {

        ResponseEntity<ResponseDTO> responseEntity = new StatusApiDelegateImpl().ping();

        assertEquals("status", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("message", "Server is up!", responseEntity.getBody().getMessage());
        assertEquals("code", "OK", responseEntity.getBody().getCode());

    }

}
