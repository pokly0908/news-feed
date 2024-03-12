package com.sparta.newsfeed.controller;

import com.sparta.newsfeed.dto.ProductRequestDto;
import com.sparta.newsfeed.dto.ProductResponseDto;
import com.sparta.newsfeed.security.UserDetailsImpl;
import com.sparta.newsfeed.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    @PostMapping("/product") // JSON 데이터와 이미지를 함께 업로드하기 위해 @ModelAttribute 애너테이션 사용
    public ProductResponseDto createProduct(@ModelAttribute ProductRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return productServiceImpl.createProduct(requestDto, userDetails);
    }

    @GetMapping("/product")
    public List<ProductResponseDto> getProduct() {
        return productServiceImpl.getProduct();
    }

    @GetMapping("/product/{productId}")
    public List<ProductResponseDto> getProductById(@PathVariable Long productId) {
        return productServiceImpl.getProductById(productId);
    }

    @GetMapping("/product/search")
    public List<ProductResponseDto> searchProduct(@RequestParam String param) {
        return productServiceImpl.searchProduct(param);
    }

    @PutMapping("/product/{productId}")
    public Long updateProduct(@PathVariable Long productId,
        @ModelAttribute ProductRequestDto requestDto) {
        return productServiceImpl.updateProduct(productId, requestDto);
    }

    @DeleteMapping("/product/{productId}")
    public Long deleteProduct(@PathVariable Long productId) {
        return productServiceImpl.deleteProduct(productId);
    }
}
