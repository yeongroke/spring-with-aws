package com.yrkim.springwithaws.common.exception;

import com.yrkim.springwithaws.common.exception.response.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidException extends BaseRuntimeException {

    private final ErrorCode errorCode;

    public InvalidException(String message, ErrorCode errorCode, Throwable throwable) {
        super(message, errorCode, throwable);
        this.errorCode = errorCode;
    }

    public InvalidException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
