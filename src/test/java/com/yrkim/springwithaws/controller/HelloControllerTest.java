package com.yrkim.springwithaws.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

/*
* assertThat
* assertj라는 테스트 검증 라이브러리의 검증 메서드이다.
* 메소드 체이닝이 지원되어 isEqualsTo와 같은 메서드를 이어서 사용가능하다.
* */


@Import(value = {HelloController.class})
@SpringBootTest
@AutoConfigureWebMvc
@AutoConfigureMockMvc(addFilters = false)
class HelloControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void hello1_조회테스트() throws Exception {
        // given
        String answer = "Yeongroke";

        mvc.perform(get("/h"))
                .andExpect(status().isOk())
                .andExpect(content().string(answer));
    }
    /*
    * param
    * API 테스트 시 요청 파라미터를 설정한다.
    * 단 String만 설정가능
    *
    * jsonPath
    * JSON 응답 값을 필드별로 검증할 수 있는 메소드이다.
    * $을 기준으로 필드명을 명시한다.
    * */
    @Test
    public void hello2_조회테스트() throws Exception {
        // given
        String name = "Good";

        mvc.perform(
                get("/h/hello")
                .param("name", name)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)));
    }
}