package com.sparta.newsfeed.repository;


import static org.assertj.core.api.Assertions.assertThat;

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


    @Autowired private UserRepository userRepository;

    @DisplayName("유저 저장")
    @Test
    void saveUser(){
        User user = new User("닉네임", "email@email.com", "password", "자기소개");
        User userSave = userRepository.save(user);
        System.out.println("usersave = " + userSave);
    }


}
