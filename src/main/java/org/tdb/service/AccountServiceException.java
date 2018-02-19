package org.tdb.service;

public class AccountServiceException extends ServiceException {

    private AccountServiceException(ServiceErrorCode errorCode) {
        super(errorCode);
    }

    @Override
    String getMessageForErrorCode() {
        switch (getErrorCode()) {
            case EMAIL_TAKEN:
                return "This email address is already associated with a different account.";
            case NAME_TAKEN:
                return "An account with this name already exists.";
            case NOT_AUTHORIZED:
                return "You do not have access to this account.";
            default: return "N/A";
        }
    }

    public static AccountServiceException withNameTaken() {
        return new AccountServiceException(ServiceErrorCode.NAME_TAKEN);
    }

    public static AccountServiceException withEmailTaken() {
        return new AccountServiceException(ServiceErrorCode.EMAIL_TAKEN);
    }

    public static AccountServiceException withNotAuthorized() {
        return new AccountServiceException(ServiceErrorCode.NOT_AUTHORIZED);
    }

}
