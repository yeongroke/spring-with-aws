package com.yrkim.springwithaws.common.security;

import com.yrkim.springwithaws.auth.service.CustomUserDetailsService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/*
 * JwtTokenProvider => jwt 토큰 생성 및 검증
 * 참고 : https://www.javainuse.com/spring/boot-jwt
 *
 * @Bean : 개발자가 컨트롤이 불가능한 외부 라이브러리들을 Bean으로 등록하고 싶은 경우에 사용
 * @Component: 개발자가 직접 작성한 클래스를 bean 등록하고자 할 경우 사용
 * */
@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider implements Serializable {
    private static final long serialVersionUID = 8343206758213279683L;

    private static final String AUTHORITIES_KEY = "auth";

    @Value("${jwt.secret}")
    private String secret;

    public static final long REFRESH_TOKEN_EXPIRE_TIME = 60 * 60 * 24 * 7;  // 7일
    public static final long ACCESS_TOKEN_EXPIRE_TIME = 5 * 60 * 60; // 5분

    public static final String ACCESS_TOKEN_NAME = "accessToken";
    public static final String REFRESH_TOKEN_NAME = "refreshToken";


    private final CustomUserDetailsService userDetailsService;

    // 액세스 토큰 생성
    public String generateToken(String userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails);
    }

    // Json Web Token 생성
    public String doGenerateToken(Map<String, Object> claims, String subject) {

        long now = (new Date()).getTime(); // 현재 시간
        Date validity = new Date(now + this.REFRESH_TOKEN_EXPIRE_TIME); // + 유효 시간 정해서 만료기간 설정

        String token = Jwts.builder()
                .setClaims(claims) // claim 넣기 - 사용자에 대한 프로퍼티/속성. 회원을 구분할 수 있는 값을 세팅
                .setSubject(subject)
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)), SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
        log.info("jwt: {}" , token);

        return token;
    }

    // jwt 토큰으로 인증 정보를 조회
    public UsernamePasswordAuthenticationToken getAuthentication(String username) {
        CustomUserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
    }

    public static Optional<String> getCurrentUserId() {
        // doFilterInternal 메소드에서 Request가 들어올 때 Context에 Authentication 객체를 저장해서 사용용
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null) {
            log.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }
        String userId = null;

        if(authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            userId = springSecurityUser.getUsername();
        }else if (authentication.getPrincipal() instanceof  String) {
            userId = (String) authentication.getPrincipal();
        }

        return Optional.ofNullable(userId);
    }

    // jwt 토큰으로부터 username(CustomUserDetails의 ID를 말함.unique) 가져오기
    public String getUserIdFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // Function<Claims, T> claimsResolver : Claims 타입의 인자를 받고, T타입의 객체 리턴하는 함수형 인터페이스
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // jwt token에서 정보가져오기
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // 토큰이 만료되었는지 확인
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // jwt token의 만료날짜 가져오기
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // 토큰 유효성 검증
    // 토큰 검증
    public Boolean validateToken(String token, String userDetailName) {
        final String username = getUserIdFromToken(token);
        return (username.equals(userDetailName) && !isTokenExpired(token));
    }
}
