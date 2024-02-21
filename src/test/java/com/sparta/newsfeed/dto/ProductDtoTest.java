package com.sparta.newsfeed.dto;


import static org.assertj.core.api.Assertions.assertThat;

import com.sparta.newsfeed.entity.Product;
import com.sparta.newsfeed.entity.User;
import com.sparta.newsfeed.security.UserDetailsImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

@DisplayName("Product Dto Test")
public class ProductDtoTest {
    User user = new User("닉네임", "email@email.com", "password", "자기소개");
    MultipartFile file = null;


    @DisplayName("상품 생성")
    @Test
    void ProductCreateTest(){
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

    @DisplayName("상품 수정")
    @Test
    void ProductUpdateTest(){
        ProductRequestDto requestDto = new ProductRequestDto("category", "title", "productInfo", 10000, file);
        Product product = new Product(requestDto, new UserDetailsImpl(user), "NULL");
        ProductRequestDto newRequestDto = new ProductRequestDto("newcategory", "newtitle", "newproductInfo", 20000, file);
        product.update(newRequestDto);

        assertThat(product.getUser()).isEqualTo(user);
        assertThat(product.getUsername()).isEqualTo("email@email.com");
        assertThat(product.getCategory()).isEqualTo("newcategory");
        assertThat(product.getTitle()).isEqualTo("newtitle");
        assertThat(product.getProductInfo()).isEqualTo("newproductInfo");
        assertThat(product.getPrice()).isEqualTo(20000);
        assertThat(product.getImageUrl()).isEqualTo("NULL");

    }

}
