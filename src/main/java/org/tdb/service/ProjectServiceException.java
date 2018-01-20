package org.tdb.service;

public class ProjectServiceException extends Exception {

    public enum ErrorCode {
        NOT_AUTHORIZED,
    }

    private ErrorCode errorCode;

    public ProjectServiceException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        switch (errorCode) {
            case NOT_AUTHORIZED:
                return "You do not have access to this project.";
            default: return "N/A";
        }
    }
}