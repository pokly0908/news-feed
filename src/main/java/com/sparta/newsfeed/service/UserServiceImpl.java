package com.sparta.newsfeed.service;

import com.sparta.newsfeed.dto.NotificationResponseDto;
import com.sparta.newsfeed.dto.user.UserProfileRequest;
import com.sparta.newsfeed.dto.user.UserProfileResponse;
import com.sparta.newsfeed.dto.user.UserSignupRequest;
import com.sparta.newsfeed.entity.Notification;
import com.sparta.newsfeed.entity.User;
import com.sparta.newsfeed.exception.DuplicationException;
import com.sparta.newsfeed.exception.NotEqualException;
import com.sparta.newsfeed.exception.NotFoundException;
import com.sparta.newsfeed.repository.NotificationRepository;
import com.sparta.newsfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final NotificationRepository notificationRepository;

    @Override
    @Transactional
    public void signup(UserSignupRequest request) {
        String nickname = request.getNickname();
        String email = request.getEmail();
        String password = passwordEncoder.encode(request.getPassword());
        String userinfo = request.getUserinfo();

        if (userRepository.findByEmail(email).isPresent()) {
            throw new DuplicationException("중복된 email 입니다.");
        }

        if (userRepository.findByNickname(nickname).isPresent()) {
            throw new DuplicationException("중복된 nickname 입니다.");
        }

        User user = new User(nickname, email, password, userinfo);
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfileResponse getProfile(User user) {
        User findUser = userRepository.findById(user.getUserId())
            .orElseThrow(() -> new NotFoundException("존재하지 않는 회원입니다."));

        return new UserProfileResponse(findUser);
    }

    @Override
    @Transactional
    public UserProfileResponse updateProfile(User user, UserProfileRequest request) {
        User findUser = userRepository.findById(user.getUserId())
            .orElseThrow(() -> new NotFoundException("존재하지 않는 회원입니다."));

        if (!passwordEncoder.matches(request.getPassword(), findUser.getPassword())) {
            throw new NotEqualException("비밀번호가 다릅니다");
        }

        user.updateProfile(request);

        return new UserProfileResponse(user);
    }

    @Override
    @Transactional
    public List<NotificationResponseDto> getNotifications(User user) {
        User findUser = userRepository.findById(user.getUserId())
            .orElseThrow(() -> new NotFoundException("존재하지 않는 회원입니다."));
        List<Notification> notifications = notificationRepository.findByUserUserIdAndReadStatusFalse(
            findUser.getUserId());
        for (Notification notification : notifications) {
            notification.setReadStatus(true);
        }
        return notifications.stream().map(NotificationResponseDto::new).toList();
    }
}
