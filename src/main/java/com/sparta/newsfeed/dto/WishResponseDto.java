package com.sparta.newsfeed.dto;

import com.sparta.newsfeed.entity.Product;
import com.sparta.newsfeed.entity.User;
import com.sparta.newsfeed.entity.Wish;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class WishResponseDto {
    private Long userId;
    private String userNickname;
    private Long productId;
    private String productCategory;

    public WishResponseDto(User user, Product product){
        this.userId = user.getUserId();
        this.userNickname = user.getNickname();
        this.productId = product.getProductId();
        this.productCategory = product.getCategory();
    }
}
