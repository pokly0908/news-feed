package com.sparta.newsfeed.repository;

import com.sparta.newsfeed.dto.CommentResponseDto;
import com.sparta.newsfeed.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByProductProductId(Long productId);

    List<Comment> findByUserUserId(Long userId);
}
