package com.sparta.newsfeed.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import com.sparta.newsfeed.dto.ProductRequestDto;
import com.sparta.newsfeed.entity.Product;
import com.sparta.newsfeed.entity.User;
import com.sparta.newsfeed.repository.ProductRepository;
import com.sparta.newsfeed.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    UploadService uploadService;

    @Test
    @DisplayName("상품정보 수정")
    void productUpdate(){
        //given
        Long productId = 10L;
        User user = new User();
        MultipartFile file = null;
        ProductRequestDto productRequestDto = new ProductRequestDto("category", "title", "productInfo", 10000, file);

        Product product = new Product(productRequestDto, user, null);

        ProductServiceImpl productServiceImpl = new ProductServiceImpl(productRepository, uploadService);

        given(productRepository.findById(productId)).willReturn(Optional.of(product));
        //when
        Long result = productServiceImpl.updateProduct(productId, productRequestDto);

        //then
        assertEquals(productId, result);

    }

}
