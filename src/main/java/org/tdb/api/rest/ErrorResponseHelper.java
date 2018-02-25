package org.tdb.api.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.tdb.model.ErrorDTO;
import org.tdb.service.ServiceException;

class ErrorResponseHelper {

    static ResponseEntity resolveFromServiceException(ServiceException e) {

        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(e.getMessage());
        errorDTO.setCode(e.getErrorCode().name());

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        switch (e.getErrorCode()) {
            case NO_PROJECT_SELECTED:
            case PROJECT_DOES_NOT_EXIST:
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            case NAME_TAKEN:
                httpStatus = HttpStatus.CONFLICT;
                break;
            case NOT_AUTHORIZED:
                httpStatus = HttpStatus.FORBIDDEN;
                break;
            case DOES_NOT_EXIST:
                httpStatus = HttpStatus.NOT_FOUND;
                break;
        }

        return new ResponseEntity(errorDTO, httpStatus);
    }

}
