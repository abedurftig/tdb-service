package org.tdb.service;

public class TestRunServiceException extends ServiceException {

    private TestRunServiceException(ServiceErrorCode errorCode) {
        super(errorCode);
    }

    @Override
    String getMessageForErrorCode() {
        switch (getErrorCode()) {
            case NOT_AUTHORIZED:
                return "You do not have access to this test run.";
            default: return "N/A";
        }
    }

    public static TestRunServiceException withNotAuthorized() {
        return new TestRunServiceException(ServiceErrorCode.NOT_AUTHORIZED);
    }

}
