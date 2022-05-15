package com.yrkim.springwithaws.common.model.response;

public enum CommonResponse {

    SUCCESS(200 , "success"),
    SIGNIN(200 ,"로그인")
    ,SIGNUP(200 ,"회원가입");

    int code;
    String message;

    CommonResponse(int code , String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
