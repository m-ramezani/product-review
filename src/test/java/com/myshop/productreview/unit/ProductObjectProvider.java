package com.myshop.productreview.unit;

import com.myshop.productreview.dto.*;
import com.myshop.productreview.enums.ProductType;
import com.myshop.productreview.model.Product;
import com.myshop.productreview.model.Review;

import java.util.Collections;

public class ProductObjectProvider {
    private static final Long id = 1L;

    public static CreateProductDto createNewProductDto() {
        CreateProductDto createProductDto = CreateProductDto.of()
                .name("productName")
                .price(100000)
                .type(ProductType.ACCESSORIES)
                .commentEnabled(true)
                .commentEnabledForAllUsers(true)
                .build();
        return createProductDto;
    }

    public static ProductDto createProductDto() {
        ProductDto productDto = ProductDto.of()
                .name("productName")
                .price(100000)
                .type(ProductType.ACCESSORIES)
                .commentEnabled(true)
                .commentEnabledForAllUsers(true)
                .build();
        return productDto;
    }

    public static Product createProduct() {
        Product product = Product.of()
                .id(id)
                .name("productName")
                .price(100000)
                .type(ProductType.ACCESSORIES)
                .commentEnabled(true)
                .commentEnabledForAllUsers(true)
                .build();
        return product;
    }

    public static ProductReviewDto createProductReviewDto() {
        ReviewDto reviewDto = ReviewDto.of()
                .comment("new comment")
                .vote(5)
                .id(id)
                .build();
        ProductReviewDto productReviewDto = ProductReviewDto.builder()
                .id(id)
                .name("productName")
                .price(100000)
                .type(ProductType.ACCESSORIES)
                .commentEnabled(true)
                .commentEnabledForAllUsers(true)
                .reviews(Collections.singletonList(reviewDto))
                .build();
        return productReviewDto;
    }

    public static ProductCommentDto createProductCommentDto() {
        ProductCommentDto commentDto = ProductCommentDto.of()
                .comment("new comment")
                .vote(5)
                .build();
        return commentDto;
    }

    public static Review createReview() {
        Review review = Review.of()
                .id(id)
                .comment("new comment")
                .vote(5)
                .build();
        return review;
    }

    public static ReviewDto createReviewDto() {
        ReviewDto review = ReviewDto.of()
                .id(id)
                .comment("new comment")
                .vote(5)
                .build();
        return review;
    }

}
