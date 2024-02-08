package com.sparta.newsfeed.service;

import com.sparta.newsfeed.dto.CommentRequestDto;
import com.sparta.newsfeed.dto.CommentResponseDto;
import com.sparta.newsfeed.entity.Comment;
import com.sparta.newsfeed.entity.Product;
import com.sparta.newsfeed.entity.User;
import com.sparta.newsfeed.repository.CommentRepository;
import com.sparta.newsfeed.repository.ProductRepository;
import com.sparta.newsfeed.repository.UserRepository;
import com.sparta.newsfeed.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public List<CommentResponseDto> getCommentByProduct(Long productId) { // 특정 상품의 댓글 조회
        Product product = findProduct(productId);
        List<Comment> commentList = commentRepository.findByProductProductId(productId);
        return convertToDtoList(commentList);
    }

    public List<CommentResponseDto> getCommentByUser(Long userId) { // 특정 유저가 작성한 댓글 조회
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );
        List<Comment> commentList = commentRepository.findByUserUserId(userId);
        return convertToDtoList(commentList);
    }

    @Transactional
    public CommentResponseDto createComment(Long productId, CommentRequestDto requestDto, UserDetailsImpl userDetails) { // 특정 상품에 댓글 작성
        Product product = findProduct(productId);
        Comment comment = commentRepository.save(new Comment(requestDto, product, userDetails.getUser()));
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto, UserDetailsImpl userDetails) { // 댓글 수정
        Comment comment = findComment(commentId, userDetails);
        comment.update(requestDto);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public String deleteComment(Long commentId, UserDetailsImpl userDetails) { // 댓글 삭제
        Comment comment = findComment(commentId, userDetails);
        commentRepository.delete(comment);
        return "댓글 삭제 완료";
    }

    private Product findProduct(Long productId){
        return productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("해당 상품아이디는 존재하지 않습니다.")
        );
    }

    private Comment findComment(Long commentId, UserDetailsImpl userDetails){
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글은 존재하지 않습니다.")
        );
        if(!userDetails.getUser().getUserId().equals(comment.getUser().getUserId())){
            throw new IllegalArgumentException("작성자만 삭제/수정이 가능합니다.");
        }
        return comment;
    }

    private List<CommentResponseDto> convertToDtoList(List<Comment> commentList) {
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentResponseDtoList.add(new CommentResponseDto(comment));
        }
        return commentResponseDtoList;
    }
}
