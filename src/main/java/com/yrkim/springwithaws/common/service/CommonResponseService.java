package com.yrkim.springwithaws.common.service;

import com.yrkim.springwithaws.common.model.response.CommonResponse;
import com.yrkim.springwithaws.common.model.response.CommonResult;
import com.yrkim.springwithaws.common.model.response.ListResult;
import com.yrkim.springwithaws.common.model.response.SingleResult;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CommonResponseService {

    public <T> SingleResult<T> getSingleResult(T data, String msg) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        result.setMessage(msg);
        setSuccessResult(result);
        return result;
    }

    public <T> ListResult<T> getListResult(Page<T> pageObj, String msg) {
        ListResult<T> result = new ListResult<>();
        result.setData(pageObj.getContent());
        result.setMessage(msg);
        result.setTotalElements(pageObj.getTotalElements());
        result.setTotalPages(pageObj.getTotalPages());
        result.setNowPage(pageObj.getNumber() + 1);
        result.setPageLimit(pageObj.getSize());
        setSuccessResult(result);
        return result;
    }

    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        result.setStatus(200);
        return result;
    }

    public CommonResult getFailResult(int code) {
        CommonResult result = new CommonResult();
        result.setStatus(code);
        return result;
    }

    private void setSuccessResult(CommonResult result) {
        result.setStatus(CommonResponse.SUCCESS.getCode());
    }
}
