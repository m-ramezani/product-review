package com.myshop.productreview.service;

import com.myshop.productreview.dto.ProductCommentDto;
import com.myshop.productreview.dto.ProductReviewDto;
import com.myshop.productreview.dto.ReviewDto;
import com.myshop.productreview.exception.ProductNotFoundException;
import com.myshop.productreview.exception.ReviewNotFoundException;
import com.myshop.productreview.mapper.ProductReviewMapper;
import com.myshop.productreview.mapper.ReviewMapper;
import com.myshop.productreview.model.Product;
import com.myshop.productreview.model.Review;
import com.myshop.productreview.repository.ProductRepository;
import com.myshop.productreview.repository.ReviewRepository;
import com.myshop.productreview.validator.ProductValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductReviewServiceImpl implements ProductReviewService {
    public final static int NUMBER_OF_COMMENTS = 3;
    private final ProductReviewMapper productReviewMapper;
    private final ProductRepository productRepository;
    private final ProductValidator productValidator;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ProductReviewServiceImpl(ProductReviewMapper productReviewMapper, ProductRepository productRepository,
                                    ProductValidator productValidator, ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.productReviewMapper = productReviewMapper;
        this.productRepository = productRepository;
        this.productValidator = productValidator;
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public ProductReviewDto getProductReviewsForAdmin(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        return productReviewMapper.toDto(product);
    }

    @Override
    public ProductReviewDto getProductReviewsForUser(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        Sort sort = Sort.by(Sort.Direction.DESC, "postedAt");
        Pageable pageable = PageRequest.of(0, NUMBER_OF_COMMENTS, sort);
        product.setReviews(reviewRepository.findByProductAndApproved(product, true, pageable).toList());
        return productReviewMapper.toDto(product);
    }

    @Override
    public ProductReviewDto addComment(ProductCommentDto productCommentDto) {
        Product product = productRepository.findById(productCommentDto.getProductId())
                .orElseThrow(ProductNotFoundException::new);
        productValidator.validateBeforeComment(product, productCommentDto);
        Review review = Review.of()
                .comment(productCommentDto.getComment())
                .vote(productCommentDto.getVote())
                .product(product)
                .approved(false)
                .build();
        product.getReviews().add(review);
        reviewRepository.save(review);
        return productReviewMapper.toDto(product);
    }

    @Override
    public ReviewDto approveComment(Long reviewId, boolean approved) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFoundException::new);
        review.setApproved(approved);
        updateProduct(review);
        return reviewMapper.toDto(review);
    }

    private void updateProduct(Review review) {
        if (review.isApproved()) {
            Product product = review.getProduct();
            product.setTotalVote(product.getTotalVote() + 1);
            product.setSumVote(product.getSumVote() + review.getVote());
        }
    }
}
