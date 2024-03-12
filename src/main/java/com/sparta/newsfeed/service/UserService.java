package com.sparta.newsfeed.service;

import com.sparta.newsfeed.dto.NotificationResponseDto;
import com.sparta.newsfeed.dto.user.UserProfileRequest;
import com.sparta.newsfeed.dto.user.UserProfileResponse;
import com.sparta.newsfeed.dto.user.UserSignupRequest;
import com.sparta.newsfeed.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    /**
     * 유저 등록
     *
     * @param request 회원가입 요청정보
     */
    @Transactional
    void signup(UserSignupRequest request);

    /**
     * 프로필 조회
     *
     * @param user 유저 요청정보
     * @return 유저 프로필 조회
     */
    @Transactional(readOnly = true)
    UserProfileResponse getProfile(User user);

    /**
     * 프로필 업데이트
     *
     * @param user    유저 요청정보
     * @param request 프로필 변경내용 정보
     * @return 유저 변경된 프로필 조회
     */
    @Transactional
    UserProfileResponse updateProfile(User user, UserProfileRequest request);

    /**
     * 알림
     *
     * @param user 유저 요청정보
     * @return 유저에게 필요한 알림정보
     */
    @Transactional
    List<NotificationResponseDto> getNotifications(User user);
}
