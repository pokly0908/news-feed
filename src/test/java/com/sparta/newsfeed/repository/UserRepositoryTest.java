package com.sparta.newsfeed.repository;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.sparta.newsfeed.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DisplayName("User Repository Test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    @DisplayName("유저 저장")
    @Test
    void saveUser() {
        //given
        User user = new User("닉네임", "email@email.com", "password", "자기소개");
        //when
        User userSave = userRepository.save(user);
        //then
        assertThat(user).isEqualTo(userSave);
    }

    @DisplayName("유저 조회")
    @Test
    void loadUser() {
        //given
        User user = new User("닉네임", "email@email.com", "password", "자기소개");
        //when
        userRepository.save(user);

        User find = userRepository.findByEmail(user.getEmail()).orElse(null);
        //then
        assertThat(find).isEqualTo(user);
    }

    @DisplayName("유저 삭제")
    @Test
    void removeUser() {
        //given
        User user = new User("닉네임", "email@email.com", "password", "자기소개");
        //when
        userRepository.save(user);

        userRepository.delete(user);
        //then
        assertThat(userRepository.findAll()).isEmpty();
    }

    @DisplayName("없는 유저 찾기")
    @Test
    void findNoUser() {
        //given
        User user = new User("닉네임", "email@email.com", "password", "자기소개");
        //when
        userRepository.save(user);
        String nickname = "asd";
        //then
        assertThatThrownBy(() -> userRepository.findByNickname(nickname)
            .orElseThrow(() -> new IllegalArgumentException("")))
            .isInstanceOf(IllegalArgumentException.class);
    }


}
