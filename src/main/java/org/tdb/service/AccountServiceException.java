package org.tdb.service;

public class AccountServiceException extends ServiceException {

    public enum ErrorCode implements WithErrorCode {
        EMAIL_IS_TAKEN,
        ACCOUNT_NAME_TAKEN,
        NOT_AUTHORIZED
    }

    public AccountServiceException(ErrorCode errorCode) {
        super(errorCode);
    }

    @Override
    public String getMessage() {
        switch (((ErrorCode) getErrorCode())) {
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
