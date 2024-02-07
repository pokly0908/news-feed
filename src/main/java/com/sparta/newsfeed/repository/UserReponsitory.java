package com.sparta.newsfeed.repository;

import com.sparta.newsfeed.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReponsitory extends JpaRepository<User, Long> {

}
