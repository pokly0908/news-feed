package com.sparta.newsfeed.service;

import com.sparta.newsfeed.dto.CommentRequestDto;
import com.sparta.newsfeed.dto.CommentResponseDto;
import com.sparta.newsfeed.security.UserDetailsImpl;

import java.util.List;

public interface CommentService {

    /**
     * 상품 별 댓글 조회
     *
     * @param productId 게시글 요청정보
     * @return 해당 상품의 댓글 조회
     */
    List<CommentResponseDto> getCommentByProduct(Long productId);

    /**
     * 유저 별 댓글 조회
     *
     * @param userId 유저 요청정보
     * @return 해당 유저의 댓글 조회
     */
    List<CommentResponseDto> getCommentByUser(Long userId);

    /**
     * 댓글 작성
     *
     * @param productId   게시글 요청정보
     * @param requestDto  댓글 내용 생성 요청자
     * @param userDetails 댓글 작성자
     * @return 댓글 생성 결과
     */
    CommentResponseDto createComment(Long productId, CommentRequestDto requestDto,
        UserDetailsImpl userDetails);

    /**
     * 댓글 수정
     *
     * @param commentId   댓글 정보
     * @param requestDto  댓글 내용 생성 요청자
     * @param userDetails 댓글 작성자
     * @return 댓글 생성 결과
     */
    CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto,
        UserDetailsImpl userDetails);

    /**
     * 게시글 삭제
     *
     * @param commentId   댓글 정보
     * @param userDetails 댓글 작성자
     * @return 댓글 삭제 결과
     */
    String deleteComment(Long commentId, UserDetailsImpl userDetails);


}
