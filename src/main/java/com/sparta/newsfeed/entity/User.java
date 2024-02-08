package com.sparta.newsfeed.entity;

import com.sparta.newsfeed.dto.user.UserProfileRequest;
import jakarta.persistence.*;
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
