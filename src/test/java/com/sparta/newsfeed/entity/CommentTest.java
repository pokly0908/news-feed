package com.sparta.newsfeed.entity;


import static org.assertj.core.api.Assertions.assertThat;

import com.sparta.newsfeed.dto.CommentRequestDto;
import com.sparta.newsfeed.dto.ProductRequestDto;
import com.sparta.newsfeed.security.UserDetailsImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

@DisplayName("Comment Test")
public class CommentTest {

    @DisplayName("댓글 생성")
    @Test
    void CommentCreateTest(){
        User user = new User("닉네임", "email@email.com", "password", "자기소개");
        MultipartFile file = null;
        ProductRequestDto requestDto = new ProductRequestDto("category", "title", "productInfo", 10000, file);
        Product product = new Product(requestDto, new UserDetailsImpl(user), "NULL");
        CommentRequestDto commentRequestDto = new CommentRequestDto("댓글");

        Comment comment = new Comment(commentRequestDto, product, user);

        assertThat(comment.getContents()).isEqualTo("댓글");
        assertThat(comment.getUser()).isEqualTo(user);
        assertThat(comment.getProduct()).isEqualTo(product);
    }

}
