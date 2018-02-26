package org.tdb.service;

import java.util.Objects;

public abstract  class ServiceException extends Exception {

    private ServiceErrorCode errorCode;

    public ServiceException(ServiceErrorCode errorCode) {
        Objects.requireNonNull(errorCode, "errorCode");
        this.errorCode = errorCode;
    }

    public ServiceErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return getMessageForErrorCode();
    }

    abstract String getMessageForErrorCode();

}
