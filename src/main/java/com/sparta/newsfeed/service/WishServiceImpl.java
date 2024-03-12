package com.sparta.newsfeed.service;

import com.sparta.newsfeed.dto.WishAllResponseDto;
import com.sparta.newsfeed.entity.Product;
import com.sparta.newsfeed.entity.User;
import com.sparta.newsfeed.entity.Wish;
import com.sparta.newsfeed.exception.NotFoundException;
import com.sparta.newsfeed.exception.PermissionException;
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
public class WishServiceImpl implements WishService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final WishRepository wishRepository;

    @Override
    public void createWish(Long productId, UserDetailsImpl userDetails) {
        String email = userDetails.getUsername();

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다."));
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new NotFoundException("존재하지 않는 상품입니다."));

        Wish wish = new Wish(user, product);

        wishRepository.save(wish);
    }

    @Override
    public List<WishAllResponseDto> readWish(UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getUserId();
        List<Wish> wish = wishRepository.findAllByUser_UserId(userId);
        List<WishAllResponseDto> wishs = new ArrayList<>();
        for (Wish value : wish) {
            Product product = value.getProduct();
            wishs.add(new WishAllResponseDto(value.getWishId(), product.getProductId(),
                product.getTitle(),
                product.getProductInfo()));
        }
        return wishs;
    }

    @Override
    public void delete(Long wishId, UserDetailsImpl userDetails) {
        Wish wish = wishRepository.findById(wishId)
            .orElseThrow(() -> new NotFoundException("관심상품이 존재하지 않습니다."));
        if (!wish.getUser().getUserId().equals(userDetails.getUser().getUserId())) {
            throw new PermissionException("회원님의 관심상품이 아닙니다.");
        }
        wishRepository.delete(wish);
    }
}
