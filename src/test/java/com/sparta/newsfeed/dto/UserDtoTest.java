package com.sparta.newsfeed.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sparta.newsfeed.dto.user.UserProfileRequest;
import com.sparta.newsfeed.dto.user.UserSignupRequest;
import com.sparta.newsfeed.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("User Dto Test")
public class UserDtoTest {

    private final UserSignupRequest userSignupRequest = new UserSignupRequest("email@email.com",
        "nickname", "password", "userInfo");

    @DisplayName("유저 생성 DTO")
    @Test
    void UserSignupDto() {
        //given
        User user = new User(userSignupRequest.getNickname(), userSignupRequest.getEmail(),
            userSignupRequest.getPassword(), userSignupRequest.getUserinfo());
        //when - then
        assertThat(user.getEmail()).isEqualTo("email@email.com");
        assertThat(user.getNickname()).isEqualTo("nickname");
        assertThat(user.getPassword()).isEqualTo("password");
        assertThat(user.getUserInfo()).isEqualTo("userInfo");
    }

    @DisplayName("유저 업데이트 DTO")
    @Test
    void UserUpdateDto() {
        //given
        User user = new User(userSignupRequest.getNickname(), userSignupRequest.getEmail(),
            userSignupRequest.getPassword(), userSignupRequest.getUserinfo());
        //when
        UserProfileRequest userProfileRequest = new UserProfileRequest("password", "nnickname",
            "userIInfo");
        if (user.getPassword().equals(userProfileRequest.getPassword())) {
            user.updateProfile(userProfileRequest);
        } else {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        //then
        assertThat(user.getNickname()).isEqualTo("nnickname");
        assertThat(user.getUserInfo()).isEqualTo("userIInfo");

    }

}
