package com.sparta.newsfeed.repository;

import com.sparta.newsfeed.entity.Product;
import com.sparta.newsfeed.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductByProductId(Long productId);

    List<Product> findAllByOrderByModifiedAtDesc();
}

