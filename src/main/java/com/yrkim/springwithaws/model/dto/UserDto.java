package com.yrkim.springwithaws.model.dto;

import com.yrkim.springwithaws.model.entity.Role;
import com.yrkim.springwithaws.model.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseTimeDto {
    @Schema(name = "id", description = "회원번호", example = "1")
    private Long id;
    @NotNull
    @Schema(name = "username", description = "유저 이름", example = "김아무개")
    private String username;
    @NotNull
    @Schema(name = "password", description = "패스워드", example = "아무개?")
    private String password;
    @NotNull
    @Email
    @Schema(name = "email", description = "이메일", example = "chfaos153@naver.com")
    private String email;
    @Schema(name = "role", description = "권한", example = "")
    private Role role;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    public User toEntity() {
        return User.builder()
                .id(this.id)
                .username(this.username)
                .password(this.password)
                .email(this.email)
                .role(this.role)
                .build();
    }
}
