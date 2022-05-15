package com.yrkim.springwithaws.common.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
public class ListResult<T> extends CommonResult {
    @Schema(name = "응답 데이터")
    private Collection<T> data = new ArrayList<>();
    @Schema(name = "응답 메시지")
    private String message;
    private long totalPages;
    private long nowPage;
    private long totalElements;
    private long pageLimit;
}
