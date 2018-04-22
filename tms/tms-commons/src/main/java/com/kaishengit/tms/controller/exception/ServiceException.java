package com.kaishengit.tms.controller.exception;

/**
 * “µŒÒ“Ï≥£
 * @author fankay
 */
public class ServiceException extends RuntimeException {

    public ServiceException(){}

    public ServiceException(Throwable th) {
        super(th);
    }

    public ServiceException(String message,Throwable th) {
        super(message,th);
    }

    public ServiceException(String message) {
        super(message);
    }
}
