package com.sparta.newsfeed.dto;

import com.sparta.newsfeed.entity.Product;
import com.sparta.newsfeed.entity.Wish;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WishAllResponseDto {

    private Long productId;
    private String title;
    private String productInfo;

    public WishAllResponseDto(Long productId, String title, String productInfo) {
        this.productId = productId;
        this.title = title;
        this.productInfo = productInfo;
    }
}
