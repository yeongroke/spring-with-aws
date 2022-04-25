package com.yrkim.springwithaws.common.exception.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Common 예외
    INVALID_INPUT(400, "C001", "잘못된 입력 값 입니다."),
    INTERNAL_SERVER_ERROR(500, "C002", "서버 오류"),
    ACCESS_DENIED_ERROR(403, "C003", "잘못된 접근 입니다."),

    // User 예외
    USER_NOT_CREATE(400, "U01", "허용된 회원가입 정보가 아닙니다."),
    LOGIN_INVALID(401, "U02", "이메일과 아이디를 확인해주세요"),
    USER_NOT_FOUND(400, "U03", "존재하지 않은 회원입니다."),
    USER_IS_EXIST(400, "U04", "이미 존재하는 회원입니다.");

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
