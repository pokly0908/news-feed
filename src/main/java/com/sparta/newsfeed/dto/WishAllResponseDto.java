package com.sparta.newsfeed.dto;

import com.sparta.newsfeed.entity.Product;
import com.sparta.newsfeed.entity.Wish;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class WishAllResponseDto {

    private List<Wish> wishs = new ArrayList<>();
    public WishAllResponseDto(List<Wish> wishs) {
        this.wishs = wishs;
    }
}
