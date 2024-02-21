package com.sparta.newsfeed.entity;

import static java.lang.constant.ConstantDescs.NULL;

import com.sparta.newsfeed.dto.ProductRequestDto;
import com.sparta.newsfeed.security.UserDetailsImpl;
import jakarta.validation.constraints.Null;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;
import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Product Test")
public class ProductTest {




    @DisplayName("상품 생성자")
    @Test
    void ProductCreateTest(){
        User user = new User("닉네임", "email@email.com", "password", "자기소개");
        MultipartFile file = null;
        ProductRequestDto requestDto = new ProductRequestDto("category", "title", "productInfo", 10000, file);
        Product product = new Product(requestDto, new UserDetailsImpl(user), "NULL");

        assertThat(product.getUser()).isEqualTo(user);
        assertThat(product.getUsername()).isEqualTo("email@email.com");
        assertThat(product.getCategory()).isEqualTo("category");
        assertThat(product.getTitle()).isEqualTo("title");
        assertThat(product.getProductInfo()).isEqualTo("productInfo");
        assertThat(product.getPrice()).isEqualTo(10000);
        assertThat(product.getImageUrl()).isEqualTo("NULL");

    }

}
