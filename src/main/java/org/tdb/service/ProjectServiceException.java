package org.tdb.service;

public class ProjectServiceException extends ServiceException {

    private ProjectServiceException(ServiceErrorCode errorCode) {
        super(errorCode);
    }

    @Override
    public String getMessageForErrorCode() {
        switch (getErrorCode()) {
            case NOT_AUTHORIZED:
                return "You do not have access to this project.";
            case DOES_NOT_EXIST:
                return "The project does not exist.";
            default: return "N/A";
        }
    }

    public static ProjectServiceException withNotAuthorized() {
        return new ProjectServiceException(ServiceErrorCode.NOT_AUTHORIZED);
    }

    public static ProjectServiceException withDoesNotExist() {
        return new ProjectServiceException(ServiceErrorCode.DOES_NOT_EXIST);
    }

}