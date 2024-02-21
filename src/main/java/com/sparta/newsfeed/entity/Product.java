package com.sparta.newsfeed.entity;


import com.sparta.newsfeed.dto.ProductRequestDto;
import com.sparta.newsfeed.security.UserDetailsImpl;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "product")
@NoArgsConstructor
public class Product extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String productInfo;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String imageUrl;

    public Product(ProductRequestDto requestDto, UserDetailsImpl userDetails, String imageUrl) {
        this.user = userDetails.getUser();
        this.username = userDetails.getUsername();
        this.category = requestDto.getCategory();
        this.title = requestDto.getTitle();
        this.productInfo = requestDto.getProductInfo();
        this.price = requestDto.getPrice();
        this.imageUrl = imageUrl;
    }
    public Product(ProductRequestDto requestDto, User user, String imageUrl) {
        this.user = user;
        this.username = user.getEmail();
        this.category = requestDto.getCategory();
        this.title = requestDto.getTitle();
        this.productInfo = requestDto.getProductInfo();
        this.price = requestDto.getPrice();
        this.imageUrl = imageUrl;
    }

    public void update(ProductRequestDto requestDto) {
        this.category = requestDto.getCategory();
        this.title = requestDto.getTitle();
        this.productInfo = requestDto.getProductInfo();
        this.price = requestDto.getPrice();
    }
}
