package com.sparta.newsfeed.repository;

import com.sparta.newsfeed.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNickname(String userNickname);
}
