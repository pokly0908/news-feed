package com.sparta.newsfeed.controller;

import com.sparta.newsfeed.dto.ProductRequestDto;
import com.sparta.newsfeed.dto.ProductResponseDto;
import com.sparta.newsfeed.security.UserDetailsImpl;
import com.sparta.newsfeed.service.ProductServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Page<ProductResponseDto> getProduct(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productServiceImpl.getProduct(pageable);
    }

    @GetMapping("/product/{productId}") //상품ID로 낱개 조회
    public List<ProductResponseDto> getProductById(@PathVariable Long productId) {
        return productServiceImpl.getProductById(productId);
    }

    @GetMapping("/product/search") //상품검색
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
