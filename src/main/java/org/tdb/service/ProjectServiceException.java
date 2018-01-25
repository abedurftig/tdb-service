package org.tdb.service;

public class ProjectServiceException extends ServiceException {

    public enum ErrorCode implements WithErrorCode {
        NOT_AUTHORIZED,
    }

    public ProjectServiceException(ErrorCode errorCode) {
        super(errorCode);
    }

    @Override
    public String getMessage() {
        switch (((ErrorCode) getErrorCode())) {
            case NOT_AUTHORIZED:
                return "You do not have access to this project.";
            default: return "N/A";
        }
    }
}