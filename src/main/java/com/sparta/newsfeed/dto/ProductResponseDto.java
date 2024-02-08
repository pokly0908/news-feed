package com.sparta.newsfeed.dto;

import com.sparta.newsfeed.entity.Product;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductResponseDto {
    private Long productId;
    private String username;
    private String category;
    private String title;
    private String productInfo;
    private int price;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ProductResponseDto(Product saveProduct) {
        this.productId = saveProduct.getProductId();
        this.username = saveProduct.getUsername();
        this.category = saveProduct.getCategory();
        this.title = saveProduct.getTitle();
        this.productInfo = saveProduct.getProductInfo();
        this.price = saveProduct.getPrice();
        this.createdAt = saveProduct.getCreatedAt();
        this.modifiedAt = saveProduct.getModifiedAt();
    }
}
