package com.sparta.newsfeed.controller;

import com.sparta.newsfeed.dto.CommentRequestDto;
import com.sparta.newsfeed.dto.CommentResponseDto;
import com.sparta.newsfeed.security.UserDetailsImpl;
import com.sparta.newsfeed.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/product/{productId}") // 특정 상품의 댓글 조회
    public List<CommentResponseDto> getCommentByProduct(@PathVariable Long productId){
        return commentService.getCommentByProduct(productId);
    }

    @GetMapping("/user/{userId}") // 특정 유저가 작성한 댓글 조회
    public List<CommentResponseDto> getCommentByuser(@PathVariable Long userId){
        return commentService.getCommentByUser(userId);
    }

    @PostMapping("/product/{productId}") // 특정 상품에 댓글 작성
    public CommentResponseDto createComment(@PathVariable Long productId, @RequestBody CommentRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(productId, requestDto, userDetails);
    }

    @PutMapping("/{commentId}") // 댓글 수정
    public CommentResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(commentId, requestDto, userDetails);
    }

    @DeleteMapping("/{commentId}") // 댓글 삭제
    public String deleteComment(@PathVariable Long commentId,
                                @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.deleteComment(commentId, userDetails);
    }

}
