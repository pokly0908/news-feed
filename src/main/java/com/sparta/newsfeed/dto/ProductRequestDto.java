package com.sparta.newsfeed.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductRequestDto {
    private String category;
    private String title;
    private String productInfo;
    private int price;
    private MultipartFile image;
}
