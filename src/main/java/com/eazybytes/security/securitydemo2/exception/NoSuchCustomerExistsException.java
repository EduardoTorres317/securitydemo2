package com.eazybytes.security.securitydemo2.exception;

public class NoSuchCustomerExistsException extends RuntimeException {
    public NoSuchCustomerExistsException(String message) {
        super(message);
    }
}
