package com.sparta.newsfeed.controller;

import com.sparta.newsfeed.dto.CommentRequestDto;
import com.sparta.newsfeed.dto.CommentResponseDto;
import com.sparta.newsfeed.security.UserDetailsImpl;
import com.sparta.newsfeed.service.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentServiceImpl commentServiceImpl;

    @GetMapping("/product/{productId}") // 특정 상품의 댓글 조회
    public List<CommentResponseDto> getCommentByProduct(@PathVariable Long productId) {
        return commentServiceImpl.getCommentByProduct(productId);
    }

    @GetMapping("/user/{userId}") // 특정 유저가 작성한 댓글 조회
    public List<CommentResponseDto> getCommentByuser(@PathVariable Long userId) {
        return commentServiceImpl.getCommentByUser(userId);
    }

    @PostMapping("/product/{productId}") // 특정 상품에 댓글 작성
    public CommentResponseDto createComment(@PathVariable Long productId,
        @RequestBody CommentRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentServiceImpl.createComment(productId, requestDto, userDetails);
    }

    @PutMapping("/{commentId}") // 댓글 수정
    public CommentResponseDto updateComment(@PathVariable Long commentId,
        @RequestBody CommentRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentServiceImpl.updateComment(commentId, requestDto, userDetails);
    }

    @DeleteMapping("/{commentId}") // 댓글 삭제
    public String deleteComment(@PathVariable Long commentId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentServiceImpl.deleteComment(commentId, userDetails);
    }

}
