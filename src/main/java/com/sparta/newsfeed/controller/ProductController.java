package com.sparta.newsfeed.controller;

import com.sparta.newsfeed.dto.ProductRequestDto;
import com.sparta.newsfeed.dto.ProductResponseDto;
import com.sparta.newsfeed.security.UserDetailsImpl;
import com.sparta.newsfeed.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/product")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return productService.createProduct(requestDto, userDetails);
    }

    @GetMapping("/product")
    public List<ProductResponseDto> getProduct() {
        return productService.getProduct();
    }

    @GetMapping("/product/{productId}")
    public List<ProductResponseDto> getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @PutMapping("/product/{productId}")
    public Long updateProduct(@PathVariable Long productId, @RequestBody ProductRequestDto requestDto) {
        return productService.updateProduct(productId, requestDto);
    }

    @DeleteMapping("/product/{productId}")
    public Long deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }
}
