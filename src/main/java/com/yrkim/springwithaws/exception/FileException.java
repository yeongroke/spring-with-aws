package com.yrkim.springwithaws.exception;

public class FileException extends RuntimeException {
    public FileException(String msg, Throwable t) {
        super(msg, t);
    }

    public FileException(String msg) {
        super(msg);
    }

    public FileException() {
        super();
    }
}
