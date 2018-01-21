package org.tdb.service;

public class DashboardServiceException extends ServiceException {

    public enum ErrorCode implements WithErrorCode {
        NAME_TAKEN,
        PROJECT_DOES_NOT_EXIST
    }

    public DashboardServiceException(ErrorCode errorCode) {
        super(errorCode);
    }

    @Override
    public String getMessage() {
        switch (((ErrorCode) getErrorCode())) {
            case NAME_TAKEN:
                return "A Dashboard with this name already exists.";
            case PROJECT_DOES_NOT_EXIST:
                return "The project which has been referenced does not exist.";
            default: return "N/A";
        }
    }

    public static DashboardServiceException withNameTaken() {
        return new DashboardServiceException(ErrorCode.NAME_TAKEN);
    }

    public static DashboardServiceException withProjectDoesNotExist() {
        return new DashboardServiceException(ErrorCode.PROJECT_DOES_NOT_EXIST);
    }

}
