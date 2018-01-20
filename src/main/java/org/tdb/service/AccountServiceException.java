package org.tdb.service;

public class AccountServiceException extends Exception {

    public enum ErrorCode {
        EMAIL_IS_TAKEN,
        ACCOUNT_NAME_TAKEN,
        NOT_AUTHORIZED
    }

    private ErrorCode errorCode;

    public AccountServiceException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        switch (errorCode) {
            case EMAIL_IS_TAKEN:
                return "This email address is already associated with a different account.";
            case ACCOUNT_NAME_TAKEN:
                return "An account with this name already exists.";
            case NOT_AUTHORIZED:
                return "You do not have access to this account.";
            default: return "N/A";
        }
    }
}
