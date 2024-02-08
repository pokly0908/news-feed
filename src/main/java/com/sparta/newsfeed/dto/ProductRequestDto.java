package com.sparta.newsfeed.dto;

import lombok.Getter;

@Getter
public class ProductRequestDto {
    private String category;
    private String title;
    private String productInfo;
    private int price;
}
