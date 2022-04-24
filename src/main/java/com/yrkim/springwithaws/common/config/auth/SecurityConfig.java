package com.yrkim.springwithaws.common.config.auth;

import com.yrkim.springwithaws.common.security.JwtAccessDeniedHandler;
import com.yrkim.springwithaws.common.security.JwtAuthenticationEntryPoint;
import com.yrkim.springwithaws.common.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/*
 * EnableWebSecurity :
 *   스프링시큐리티 활성화
 *   내부에 @Configuration 있기 때문에 생략 가능
 * EnableGlobalMethodSecurity(prePostEnabled = true) :
 *   Controller에서 특정 페이지에 특정 권한이 있는 유저만 접근을 허용할 경우
 *   @PreAuthorize 어노테이션을 사용하는데,
 *   해당 어노테이션에 대한 설정을 활성화 (필수x)
 * */
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtFilter jwtFilter;

    private static final String[] PERMIT_POST_PATHS = {
            ""
    };
    private static final String[] PERMIT_GET_PATHS = {
            "/szs/me"
    };

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(
                        "/static/**",
                        "/resources/**",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/h2/**",
                        "/swagger-resources/**",
                        "/swagger/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // cors 설정
            .cors().configurationSource(corsConfigurationSource())
        .and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, PERMIT_POST_PATHS).permitAll()
            .antMatchers(HttpMethod.GET, PERMIT_GET_PATHS).permitAll()
            // 그외 다른요청은 인증 필요
            .anyRequest().authenticated()
        .and()
            .exceptionHandling()
            // 인증과정에서 실패 401
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler)
        .and()
            // JWT 인증에는 기본으로 세션을 사용하지 않기 때문에 stateless를 사용
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }



    // cors 속성
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        String[] exposeHeaders = {
                "*"};
        configuration.setAllowedOrigins(Arrays.asList(
                "*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList(exposeHeaders));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}