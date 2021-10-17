package com.myshop.productreview.service;

import com.myshop.productreview.dto.ProductCommentDto;
import com.myshop.productreview.dto.ProductReviewDto;
import com.myshop.productreview.dto.ReviewDto;

public interface ProductReviewService {

    ProductReviewDto getProductReviewsForAdmin(Long productId);

    ProductReviewDto getProductReviewsForUser(Long productId);

    ProductReviewDto addComment(ProductCommentDto productCommentDto);

    ReviewDto approveComment(Long reviewId, boolean approved);
}
