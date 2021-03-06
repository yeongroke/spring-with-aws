package com.yrkim.springwithaws.auth.exception;

import com.yrkim.springwithaws.common.exception.BaseRuntimeException;
import com.yrkim.springwithaws.common.exception.response.ErrorCode;

public class UserNotFoundException extends BaseRuntimeException {
    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
