package com.sparta.newsfeed.service;

import com.sparta.newsfeed.dto.user.UserProfileResponse;
import com.sparta.newsfeed.dto.user.UserSignupRequest;
import com.sparta.newsfeed.entity.User;
import com.sparta.newsfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(UserSignupRequest request) {
        String nickname = request.getNickname();
        String email = request.getEmail();
        String password = passwordEncoder.encode(request.getPassword());
        String userinfo = request.getUserinfo();


        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("중복된 email 입니다.");
        }

        if (userRepository.findByNickname(nickname).isPresent()) {
            throw new IllegalArgumentException("중복된 nickname 입니다.");
        }

        User user = new User(nickname, email, password, userinfo);
        userRepository.save(user);
    }

    public UserProfileResponse getProfile(User user) {
        User findUser = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        return new UserProfileResponse(findUser);
    }
}
