package com.yrkim.springwithaws.common.utils;

import com.yrkim.springwithaws.common.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 토큰을 쿠키 형태로 저장하기 위해 사용하는 클래스
 */
@Service
@RequiredArgsConstructor
public class CookieUtil {

    private final TokenProvider tokenProvider;

    public Cookie createCookie(String cookieName, String value, int age){
        Cookie token = new Cookie(cookieName,value);
        token.setHttpOnly(true); // 쿠키는 클라이언트 측 스크립트에 접근x. 악성 스크립트(XSS 공격)에 의한 위험 완화
        token.setMaxAge(age); // 쿠키 유효기간 설정
        token.setPath("/"); // 모든 경로에서 접근 가능하도록 설정
        return token;
    }

    public Cookie getCookie(HttpServletRequest req, String cookieName){
        final Cookie[] cookies = req.getCookies();
        if(cookies==null) return null;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(cookieName))
                return cookie;
        }
        return null;
    }

    public Cookie createAccessToken(String subject) {
        final String accessJwt = tokenProvider.generateToken(subject);
        Cookie accessToken = this.createCookie(tokenProvider.ACCESS_TOKEN_NAME, accessJwt, (int) tokenProvider.ACCESS_TOKEN_EXPIRE_TIME);
        return accessToken;
    }
}
