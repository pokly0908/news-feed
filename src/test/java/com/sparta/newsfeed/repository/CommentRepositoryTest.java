package com.sparta.newsfeed.repository;


import static org.assertj.core.api.Assertions.assertThat;

import com.sparta.newsfeed.dto.CommentRequestDto;
import com.sparta.newsfeed.dto.ProductRequestDto;
import com.sparta.newsfeed.entity.Comment;
import com.sparta.newsfeed.entity.Product;
import com.sparta.newsfeed.entity.User;
import com.sparta.newsfeed.security.UserDetailsImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

@ActiveProfiles("test")
@DisplayName("Comment Repository Test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CommentRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CommentRepository commentRepository;

    @DisplayName("댓글 등록")
    @Test
    void commentCreate(){
        User user = new User("닉네임", "email@email.com", "password", "자기소개");

        MultipartFile file = null;
        ProductRequestDto requestDto = new ProductRequestDto("category", "title", "productInfo", 10000, file);
        Product product = new Product(requestDto, new UserDetailsImpl(user), "NULL");

        userRepository.save(user);
        productRepository.save(product);

        CommentRequestDto commentRequestDto = new CommentRequestDto("댓글");
        Comment comment = new Comment(commentRequestDto, product, user);

        Comment saveComment = commentRepository.save(comment);

        assertThat(saveComment).isEqualTo(comment);
    }

    @DisplayName("댓글 삭제")
    @Test
    void commentDelete(){
        User user = new User("닉네임", "email@email.com", "password", "자기소개");

        MultipartFile file = null;
        ProductRequestDto requestDto = new ProductRequestDto("category", "title", "productInfo", 10000, file);
        Product product = new Product(requestDto, new UserDetailsImpl(user), "NULL");

        userRepository.save(user);
        productRepository.save(product);

        CommentRequestDto commentRequestDto = new CommentRequestDto("댓글");
        Comment comment = new Comment(commentRequestDto, product, user);
        commentRepository.save(comment);

        commentRepository.delete(comment);
        assertThat(commentRepository.findAll()).isEmpty();
    }

}
