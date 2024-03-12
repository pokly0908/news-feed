package com.sparta.newsfeed.service;

import com.sparta.newsfeed.dto.WishAllResponseDto;
import com.sparta.newsfeed.security.UserDetailsImpl;

import java.util.List;

public interface WishService {

    /**
     * 관심상품 등록
     *
     * @param productId   상품ID 요청정보
     * @param userDetails 관심상품 생성 요청자
     */
    void createWish(Long productId, UserDetailsImpl userDetails);

    /**
     * 관심상품 조회
     *
     * @param userDetails 관심상품 생성 요청자
     * @return 관심상품 리스트 조회
     */
    List<WishAllResponseDto> readWish(UserDetailsImpl userDetails);

    /**
     * 게시글 생성
     *
     * @param wishId      관심상품 삭제 요청정보
     * @param userDetails 관심상품 삭제 요청자
     */
    void delete(Long wishId, UserDetailsImpl userDetails);
}
