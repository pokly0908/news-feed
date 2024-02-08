package com.sparta.newsfeed.service;

import com.sparta.newsfeed.dto.WishAllResponseDto;
import com.sparta.newsfeed.dto.WishResponseDto;
import com.sparta.newsfeed.entity.Product;
import com.sparta.newsfeed.entity.User;
import com.sparta.newsfeed.entity.Wish;
import com.sparta.newsfeed.repository.ProductRepository;
import com.sparta.newsfeed.repository.UserRepository;
import com.sparta.newsfeed.repository.WishRepository;
import com.sparta.newsfeed.security.UserDetailsImpl;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final WishRepository wishRepository;

    public void createWish(Long productId, UserDetailsImpl userDetails) {
        String email = userDetails.getUsername();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        Wish wish = new Wish(user, product);

        wishRepository.save(wish);
    }

    public List<WishAllResponseDto> readWish(UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getUserId();
        List<Wish> wish = wishRepository.findAllByUser_UserId(userId);
        List<WishAllResponseDto> wishs = new ArrayList<>();
        for (Wish value : wish) {
            Product product = value.getProduct();
            wishs.add(new WishAllResponseDto(product.getProductId(), product.getTitle(),
                product.getProductInfo()));
        }
        return wishs;
    }
}
