package com.sparta.newsfeed.service;

import com.sparta.newsfeed.dto.ProductRequestDto;
import com.sparta.newsfeed.dto.ProductResponseDto;
import com.sparta.newsfeed.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductSerivce {

    /**
     * 상품 등록
     *
     * @param requestDto  게시글 생성 요청정보
     * @param userDetails 게시글 생성 요청자
     * @return 상품 생성 결과
     */
    @Transactional
    ProductResponseDto createProduct(@RequestParam(required = false) ProductRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails);

    /**
     * 상품 전체 조회
     *
     * @return 상품 조회 결과
     */
    List<ProductResponseDto> getProduct();

    /**
     * 상품 낱개 조회
     *
     * @param productId 상품ID
     * @return 상품 조회 결과
     */
    List<ProductResponseDto> getProductById(Long productId);

    /**
     * 상품 검색
     *
     * @param param 상품 제목
     * @return 상품 조회 결과
     */
    List<ProductResponseDto> searchProduct(String param);

    /**
     * 상품 수정
     *
     * @param productId  상품Id
     * @param requestDto 수정 내용
     * @return 수정 결과
     */
    @Transactional
    Long updateProduct(Long productId, ProductRequestDto requestDto);

    /**
     * 상품 삭제
     *
     * @param productId 상품ID
     * @return 삭제 결과
     */
    @Transactional
    Long deleteProduct(Long productId);
}
