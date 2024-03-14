package com.sparta.newsfeed.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.sparta.newsfeed.dto.ProductRequestDto;
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
@DisplayName("Product Repository Test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @DisplayName("상품 등록")
    @Test
    void productCreate() {
        //given
        User user = new User("닉네임", "email@email.com", "password", "자기소개");
        //when
        userRepository.save(user);
        MultipartFile file = null;
        ProductRequestDto requestDto = new ProductRequestDto("category", "title", "productInfo",
            10000, file);
        Product product = new Product(requestDto, new UserDetailsImpl(user), "NULL");

        Product saveProduct = productRepository.save(product);
        //then
        assertThat(saveProduct).isEqualTo(product);

    }

    @DisplayName("상품 삭제")
    @Test
    void productRemove() {
        //given
        User user = new User("닉네임", "email@email.com", "password", "자기소개");
        //when
        userRepository.save(user);
        MultipartFile file = null;
        ProductRequestDto requestDto = new ProductRequestDto("category", "title", "productInfo",
            10000, file);
        Product product = new Product(requestDto, new UserDetailsImpl(user), "NULL");

        productRepository.save(product);
        productRepository.delete(product);
        //then
        assertThat(productRepository.findAll()).isEmpty();
    }


}
