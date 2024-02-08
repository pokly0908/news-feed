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
    private final String message;
    private final HttpStatus httpStatus;
}
