package org.tdb.service;

public class ServiceException extends Exception {

    private WithErrorCode errorCode;

    public ServiceException(WithErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public WithErrorCode getErrorCode() {
        return errorCode;
    }

    public interface WithErrorCode {

        public String name();

    }

}
