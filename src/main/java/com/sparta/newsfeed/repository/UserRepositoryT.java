package com.sparta.newsfeed.repository;

import com.sparta.newsfeed.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserRepositoryT {

    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);

    List<User> search(String nickname, String email);
}
