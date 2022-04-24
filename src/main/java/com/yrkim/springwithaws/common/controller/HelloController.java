package com.yrkim.springwithaws.common.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
* @RequiredArgsConstructor
* 선언된 모든 final 필드가 포함된 생성자를 생성해준다.
* final 없으면 생성자에 포함되지 않음.
*
* */

@Slf4j
@RestController
@RequiredArgsConstructor
public class HelloController {

    @GetMapping(value = "/h")
    public String hello1() {
        return "Yeongroke";
    }

    /*
    * @RequestParam
    * 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션이다.
    * */
    @GetMapping(value = "/h/hello")
    public String hello2(@RequestParam("name") String name) {
        return "Hello Yeongroke" + name;
    }
}
