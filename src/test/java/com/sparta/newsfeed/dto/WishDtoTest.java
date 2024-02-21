package com.sparta.newsfeed.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sparta.newsfeed.entity.Product;
import com.sparta.newsfeed.entity.User;
import com.sparta.newsfeed.entity.Wish;
import com.sparta.newsfeed.security.UserDetailsImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

public class WishDtoTest {
    @DisplayName("관심상품 생성")
    @Test
    void WishCreateTest() {
        //given
        User user = new User("닉네임", "email@email.com", "password", "자기소개");

        //when
        MultipartFile file = null;
        ProductRequestDto requestDto = new ProductRequestDto("category", "title", "productInfo", 10000, file);
        Product product = new Product(requestDto, new UserDetailsImpl(user), "NULL");
        Wish wish = new Wish(user, product);
        //then
        assertThat(wish.getUser()).isEqualTo(user);
        assertThat(wish.getProduct()).isEqualTo(product);
    }
}
