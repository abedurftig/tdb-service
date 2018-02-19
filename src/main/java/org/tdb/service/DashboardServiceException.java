package org.tdb.service;

public class DashboardServiceException extends ServiceException {

    private DashboardServiceException(ServiceErrorCode errorCode) {
        super(errorCode);
    }

    @Override
    public String getMessageForErrorCode() {
        switch (getErrorCode()) {
            case NAME_TAKEN:
                return "A Dashboard with this name already exists.";
            case PROJECT_DOES_NOT_EXIST:
                return "A project which has been referenced does not exist.";
            case NO_PROJECT_SELECTED:
                return "At least one project needs to be selected for the dashboard.";
            case NOT_AUTHORIZED:
                return "You do not have access to this dashboard.";
            default: return "N/A";
        }
    }

    public static DashboardServiceException withNameTaken() {
        return new DashboardServiceException(ServiceErrorCode.NAME_TAKEN);
    }

    public static DashboardServiceException withProjectDoesNotExist() {
        return new DashboardServiceException(ServiceErrorCode.PROJECT_DOES_NOT_EXIST);
    }

    public static DashboardServiceException withoutProject() {
        return new DashboardServiceException(ServiceErrorCode.NO_PROJECT_SELECTED);
    }

    public static DashboardServiceException withNotAuthorized() {
        return new DashboardServiceException(ServiceErrorCode.NOT_AUTHORIZED);
    }

}
