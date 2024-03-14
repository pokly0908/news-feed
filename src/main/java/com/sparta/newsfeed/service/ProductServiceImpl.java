package com.sparta.newsfeed.service;

import com.sparta.newsfeed.dto.ProductRequestDto;
import com.sparta.newsfeed.dto.ProductResponseDto;
import com.sparta.newsfeed.entity.Notification;
import com.sparta.newsfeed.entity.Product;
import com.sparta.newsfeed.entity.Wish;
import com.sparta.newsfeed.exception.NotFoundException;
import com.sparta.newsfeed.repository.NotificationRepository;
import com.sparta.newsfeed.repository.ProductRepository;
import com.sparta.newsfeed.repository.WishRepository;
import com.sparta.newsfeed.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductSerivce {

    private final ProductRepository productRepository;
    private final UploadService uploadService;
    private final WishRepository wishRepository;
    private final NotificationRepository notificationRepository;

    @Override
    @Transactional
    public ProductResponseDto createProduct(
        @RequestParam(required = false) ProductRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String imageUrl = uploadService.uploadImageAndGetUrl(requestDto.getImage());
        Product product = productRepository.save(new Product(requestDto, userDetails, imageUrl));
        return new ProductResponseDto(product);
    }

    public Page<ProductResponseDto> getProduct(Pageable pageable) {
        return productRepository.findAllByOrderByModifiedAtDesc(pageable)
            .map(ProductResponseDto::new);
    }

    @Override
    public List<ProductResponseDto> getProductById(Long productId) {
        return productRepository.findProductByProductId(productId).stream()
            .map(ProductResponseDto::new).toList();
    }

    @Override
    public List<ProductResponseDto> searchProduct(String param) {
        return productRepository.findByTitleContaining(param).stream().map(ProductResponseDto::new)
            .toList();
    }

    @Override
    @Transactional
    public Long updateProduct(Long productId, ProductRequestDto requestDto) {
        Product product = findProduct(productId);
        String imageUrl = uploadService.uploadImageAndGetUrl(requestDto.getImage());
        int oldPrice = product.getPrice();
        int newPrice = requestDto.getPrice();
        product.update(requestDto, imageUrl);
        if (oldPrice != newPrice) {
            notifyPriceChange(product, oldPrice, newPrice);
        }
        return productId;
    }

    @Override
    @Transactional
    public Long deleteProduct(Long productId) {
        Product product = findProduct(productId);

        productRepository.delete(product);

        return productId;
    }

    private Product findProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() ->
            new NotFoundException("선택한 물건은 존재하지 않습니다."));
    }

    private void notifyPriceChange(Product product, int oldPrice, int newPrice) {
        List<Wish> wishList = wishRepository.findByProductProductId(product.getProductId());
        for (Wish wish : wishList) {
            Notification notification = new Notification();
            notification.setUser(wish.getUser());
            notification.setMessage("관심 상품으로 등록하신 [" + product.getTitle() + "] 의 가격이 ["
                + oldPrice + "] 에서 [" + newPrice + "] 으로 변동되었습니다!");
            notificationRepository.save(notification);
        }
    }
}
