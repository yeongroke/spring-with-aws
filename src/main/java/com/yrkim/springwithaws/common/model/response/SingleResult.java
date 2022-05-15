package com.yrkim.springwithaws.common.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResult<T> extends CommonResult {
    @Schema(name = "응답 데이터")
    private T data;
    @Schema(name = "응답 메시지")
    private String message;
}

