package com.sparta.newsfeed.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.sparta.newsfeed.dto.ProductRequestDto;
import com.sparta.newsfeed.security.UserDetailsImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;


@DisplayName("WIsh Test")
public class WishTest {

    @DisplayName("관심상품 생성")
    @Test
    void WishCreateTest() {
        User user = new User("닉네임", "email@email.com", "password", "자기소개");
        MultipartFile file = null;
        ProductRequestDto requestDto = new ProductRequestDto("category", "title", "productInfo", 10000, file);
        Product product = new Product(requestDto, new UserDetailsImpl(user), "NULL");
        Wish wish = new Wish(user, product);

        assertThat(wish.getUser()).isEqualTo(user);
        assertThat(wish.getProduct()).isEqualTo(product);
    }
}
