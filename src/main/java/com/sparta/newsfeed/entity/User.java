package com.sparta.newsfeed.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.sparta.newsfeed.dto.user.UserProfileRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String userInfo;

    public User(String nickname, String email, String password, String userinfo) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.userInfo = userinfo;
    }

    public void updateProfile(UserProfileRequest request) {
        this.nickname = request.getNickname();
        this.userInfo = request.getUserinfo();
    }
}
