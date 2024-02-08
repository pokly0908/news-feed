package com.sparta.newsfeed.dto.user;

import com.sparta.newsfeed.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserProfileResponse {

    private String email;
    private String nickname;
    private String userinfo;

    public UserProfileResponse(User findUser) {
        this.email = findUser.getEmail();
        this.nickname = findUser.getNickname();
        this.userinfo = findUser.getUserInfo();
    }
}
