package org.tdb.service;

public class DashboardServiceException extends ServiceException {

    public enum ErrorCode implements WithErrorCode {
        NAME_TAKEN,
        PROJECT_DOES_NOT_EXIST,
        NO_PROJECT_SELECTED
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
                return "A project which has been referenced does not exist.";
            case NO_PROJECT_SELECTED:
                return "At least one project needs to be selected for the dashboard.";
            default: return "N/A";
        }
    }

    public static DashboardServiceException withNameTaken() {
        return new DashboardServiceException(ErrorCode.NAME_TAKEN);
    }

    public static DashboardServiceException withProjectDoesNotExist() {
        return new DashboardServiceException(ErrorCode.PROJECT_DOES_NOT_EXIST);
    }

    public static DashboardServiceException withoutProject() {
        return new DashboardServiceException(ErrorCode.NO_PROJECT_SELECTED);
    }

}
