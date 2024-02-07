package com.sparta.newsfeed.controller;


import com.sparta.newsfeed.dto.WishAllResponseDto;
import com.sparta.newsfeed.dto.WishResponseDto;
import com.sparta.newsfeed.security.UserDetailsImpl;
import com.sparta.newsfeed.service.WishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wish")
public class WishController {

    private final WishService wishService;

    @PostMapping("/{productId}")
    public WishResponseDto createWish(@Valid @PathVariable Long productId, @AuthenticationPrincipal
        UserDetailsImpl userDetails){
        return wishService.createWish(productId, userDetails);
    }

    @GetMapping("/")
    public WishAllResponseDto readWish(@AuthenticationPrincipal
    UserDetailsImpl userDetails){
        return wishService.readWish(userDetails);
    }
}
