package com.yrkim.springwithaws.common.model.response;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CommonResult<T> {

    @Schema(name = "응답 상태")
    private T status;
}
