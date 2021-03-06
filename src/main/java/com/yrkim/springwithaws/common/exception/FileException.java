package com.yrkim.springwithaws.common.exception;

import com.yrkim.springwithaws.common.exception.response.ErrorCode;

public class FileException extends BaseRuntimeException {

    private final ErrorCode errorCode;

    public FileException(String message, ErrorCode errorCode, Throwable throwable) {
        super(message, errorCode, throwable);
        this.errorCode = errorCode;
    }

    public FileException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
