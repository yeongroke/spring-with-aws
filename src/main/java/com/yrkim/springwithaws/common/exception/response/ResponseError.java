package com.yrkim.springwithaws.common.exception.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseError {

    private String errorMessage;
    private int status;
    private String code;

    private ResponseError(final ErrorCode code) {
        this.errorMessage = code.getMessage();
        this.status = code.getStatus();
        this.code = code.getCode();
    }

    public static ResponseError of(ErrorCode code) {
        return new ResponseError(code);
    }
}
