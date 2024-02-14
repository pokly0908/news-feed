package com.sparta.newsfeed.repository;

import com.sparta.newsfeed.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserUserIdAndReadStatusFalse(Long userId);
}
