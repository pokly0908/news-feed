package com.sparta.newsfeed.repository;

import com.sparta.newsfeed.entity.Wish;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Long> {

    List<Wish> findAllByUser_UserId(Long userId);

    List<Wish> findByProductProductId(Long productId);
}
