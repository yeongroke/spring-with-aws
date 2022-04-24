package com.yrkim.springwithaws.common.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {
    private String userId;
    private String password;
    private String userName;
    private String email;
    private boolean ENABLED;

    // 계정의 권한 목록을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // role 사용 안함
        return Collections.emptyList();
    }

    // 계정의 비밀번호를 리턴
    @Override
    public String getPassword() {
        return password;
    }

    // security에서 사용하는 회원 구분 id
    // 계정의 고유한 값을 리턴
    @Override
    public String getUsername() {
        return userId;
    }

    // 계정의 만료 여부 리턴 (true : 만료 안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정의 잠김 여부 리턴 (true : 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료 여부 리턴 (true : 만료 안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정의 활성화 여부 리턴 (true : 활성화 됨)
    @Override
    public boolean isEnabled() {
        return ENABLED;
    }
}
