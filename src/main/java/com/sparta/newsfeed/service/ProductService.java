package com.sparta.newsfeed.service;

import com.sparta.newsfeed.dto.ProductRequestDto;
import com.sparta.newsfeed.dto.ProductResponseDto;
import com.sparta.newsfeed.entity.Product;
import com.sparta.newsfeed.repository.ProductRepository;
import com.sparta.newsfeed.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Product product = new Product(requestDto, userDetails);

        Product saveProduct = productRepository.save(product);

        ProductResponseDto responseDto = new ProductResponseDto(saveProduct);

        return responseDto;
    }

    public List<ProductResponseDto> getProduct() {
        return productRepository.findAllByOrderByModifiedAtDesc().stream().map(ProductResponseDto::new).toList();
    }

    public List<ProductResponseDto> getProductById(Long productId) {
        return productRepository.findProductByProductId(productId).stream().map(ProductResponseDto::new).toList();
    }
    @Transactional
    public Long updateProduct(Long productId, ProductRequestDto requestDto) {
        Product product = findProduct(productId);

        product.update(requestDto);

        return productId;
    }
    @Transactional
    public Long deleteProduct(Long productId) {
        Product product = findProduct(productId);

        productRepository.delete(product);

        return productId;
    }

    private Product findProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() ->
                new IllegalArgumentException("선택한 물건은 존재하지 않습니다."));
    }
}
