package com.yrkim.springwithaws.auth.service;

import com.yrkim.springwithaws.auth.model.entity.User;
import com.yrkim.springwithaws.auth.repository.jpa.UserRepository;
import com.yrkim.springwithaws.common.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public CustomUserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userRepository.findUsersByUserId(userId)
                .map(user -> createUser(user))
                .orElseThrow(() -> new UsernameNotFoundException(userId + "-> 데이터베이스에서 찾을 수 없습니다."));
    }

    private CustomUserDetails createUser(User user) {
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setUserId(user.getUserId());
        userDetails.setPassword(user.getPassword());
        userDetails.setUserName(user.getUserName());
        return userDetails;
    }
}
