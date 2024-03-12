package com.sparta.newsfeed.controller;


import com.sparta.newsfeed.dto.WishAllResponseDto;
import com.sparta.newsfeed.dto.WishResponseDto;
import com.sparta.newsfeed.security.UserDetailsImpl;
import com.sparta.newsfeed.service.WishServiceImpl;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wish")
public class WishController {

    private final WishServiceImpl wishService;

    @PostMapping("/{productId}")
    public ResponseEntity<WishResponseDto> createWish(@Valid @PathVariable Long productId,
        @AuthenticationPrincipal
        UserDetailsImpl userDetails) {
        wishService.createWish(productId, userDetails);
        return ResponseEntity.ok().body(new WishResponseDto("저장이 완료되었습니다", HttpStatus.OK));
    }

    @GetMapping
    public List<WishAllResponseDto> readWish(@AuthenticationPrincipal
    UserDetailsImpl userDetails) {
        return wishService.readWish(userDetails);
    }

    @DeleteMapping("/{wishId}")
    public ResponseEntity<WishResponseDto> deleteWish(@Valid @PathVariable Long wishId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        wishService.delete(wishId, userDetails);
        return ResponseEntity.ok().body(new WishResponseDto("삭제가 완료되었습니다.", HttpStatus.OK));
    }
}
