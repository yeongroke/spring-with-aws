package com.yrkim.springwithaws.common.exception;

import com.yrkim.springwithaws.common.exception.response.ErrorCode;
import lombok.Getter;

@Getter
public class BaseRuntimeException extends RuntimeException {

    private final ErrorCode errorCode;

    public BaseRuntimeException(String message, ErrorCode errorCode, Throwable throwable) {
        super(message , throwable);
        this.errorCode = errorCode;
    }

    public BaseRuntimeException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
