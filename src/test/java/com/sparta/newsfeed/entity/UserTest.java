package com.sparta.newsfeed.entity;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("User Test")
public class UserTest {

    @DisplayName("유저 생성자")
    @Test
    void UserCreateTest(){

        User user = new User("닉네임", "email@email.com", "password", "자기소개");

        assertThat(user.getNickname()).isEqualTo("닉네임");
        assertThat(user.getEmail()).isEqualTo("email@email.com");
        assertThat(user.getPassword()).isEqualTo("password");
        assertThat(user.getUserInfo()).isEqualTo("자기소개");
    }
}
