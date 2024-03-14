package com.sparta.newsfeed.repository;

import com.sparta.newsfeed.entity.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findProductByProductId(Long productId);

    Page<Product> findAllByOrderByModifiedAtDesc(Pageable pageable);

    List<Product> findByTitleContaining(String param);
}

