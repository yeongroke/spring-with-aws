package com.yrkim.springwithaws.auth.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }

    public UserNotFoundException() {
        super();
    }
}
