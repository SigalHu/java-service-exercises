package com.sigalhu.jse.maven.web.persist;

public class AccountPersistException extends Exception {
    private static final long serialVersionUID = -8334988626147289624L;

    public AccountPersistException(String message) {
        super(message);
    }

    public AccountPersistException(String message, Throwable throwable) {
        super(message, throwable);
    }
}