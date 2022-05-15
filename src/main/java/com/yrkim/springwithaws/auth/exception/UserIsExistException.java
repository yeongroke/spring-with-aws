package com.yrkim.springwithaws.auth.exception;

import com.yrkim.springwithaws.common.exception.BaseRuntimeException;
import com.yrkim.springwithaws.common.exception.response.ErrorCode;

public class UserIsExistException extends BaseRuntimeException {
    public UserIsExistException(ErrorCode errorCode) {
        super(errorCode);
    }
}
