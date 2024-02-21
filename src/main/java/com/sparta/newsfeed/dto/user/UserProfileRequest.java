package com.sparta.newsfeed.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class UserProfileRequest {

    @NotBlank
    private String password;

    @NotBlank
    private String nickname;

    @NotBlank
    private String userinfo;

}
