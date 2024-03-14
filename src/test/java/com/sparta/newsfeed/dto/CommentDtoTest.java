package com.sparta.newsfeed.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sparta.newsfeed.dto.user.UserSignRequest;
import com.sparta.newsfeed.entity.Comment;
import com.sparta.newsfeed.entity.Product;
import com.sparta.newsfeed.entity.User;
import com.sparta.newsfeed.security.UserDetailsImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

@DisplayName("Comment Dto Test")
public class CommentDtoTest {

    User user = new User("닉네임", "email@email.com", "password", "자기소개");
    MultipartFile file = null;
    ProductRequestDto requestDto = new ProductRequestDto("category", "title", "productInfo", 10000,
        file);
    Product product = new Product(requestDto, new UserDetailsImpl(user), "NULL");

    @DisplayName("댓글 생성")
    @Test
    void CommentCreateTest() {
        // given
        CommentRequestDto commentRequestDto = new CommentRequestDto("댓글");
        // when
        Comment comment = new Comment(commentRequestDto, product, user);
        //then
        assertThat(comment.getContents()).isEqualTo("댓글");
        assertThat(comment.getUser()).isEqualTo(user);
        assertThat(comment.getProduct()).isEqualTo(product);
    }

    @DisplayName("댓글 수정")
    @Test
    void commentUpdateTest() {
        // given
        CommentRequestDto commentRequestDto = new CommentRequestDto("댓글");
        Comment comment = new Comment(commentRequestDto, product, user);
        //when
        CommentRequestDto newCommentRequestDto = new CommentRequestDto("댓글수정");
        comment.update(newCommentRequestDto);
        //then
        assertThat(comment.getContents()).isEqualTo("댓글수정");
        assertThat(comment.getUser()).isEqualTo(user);
        assertThat(comment.getProduct()).isEqualTo(product);

    }


}
