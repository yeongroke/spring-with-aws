package com.yrkim.springwithaws.auth.model.entity;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum Role {
    ROLE_ADMIN,
    ROLE_USER
}
