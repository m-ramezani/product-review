package com.myshop.productreview.unit.service;

import com.myshop.productreview.dto.ProductCommentDto;
import com.myshop.productreview.dto.ProductReviewDto;
import com.myshop.productreview.exception.AccessDeniedException;
import com.myshop.productreview.exception.CommentDisabledException;
import com.myshop.productreview.exception.ReviewNotFoundException;
import com.myshop.productreview.mapper.ProductReviewMapper;
import com.myshop.productreview.mapper.ReviewMapper;
import com.myshop.productreview.model.Product;
import com.myshop.productreview.model.Review;
import com.myshop.productreview.repository.ProductRepository;
import com.myshop.productreview.repository.ReviewRepository;
import com.myshop.productreview.service.ProductReviewServiceImpl;
import com.myshop.productreview.unit.ProductObjectProvider;
import com.myshop.productreview.validator.ProductValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductReviewServiceTest {

    private PageRequest pageable;
    @InjectMocks
    private ProductReviewServiceImpl productReviewService;
    @Mock
    private ProductReviewMapper productReviewMapper;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductValidator productValidator;
    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private ReviewMapper reviewMapper;

    private int totalElements;
    private Long id;

    @Before
    public void setup() {
        id = 1L;
        totalElements = 100;
        pageable = PageRequest.of(1, 10);
    }

    @Test
    public void getProductReviewsForUser() {
        Sort sort = Sort.by(Sort.Direction.DESC, "postedAt");
        ProductReviewDto productReviewDto = ProductObjectProvider.createProductReviewDto();
        Product mockProduct = ProductObjectProvider.createProduct();
        Page<Review> reviewPage = new PageImpl<>(Collections.singletonList(ProductObjectProvider.createReview()), pageable, totalElements);
        when(productRepository.findById(id)).thenReturn(Optional.of(mockProduct));
        when(productReviewMapper.toDto(mockProduct)).thenReturn(productReviewDto);
        when(reviewRepository.findByProductAndApproved(mockProduct, true, PageRequest.of(0, 3, sort)))
                .thenReturn(reviewPage);
        ProductReviewDto result = productReviewService.getProductReviewsForUser(id);
        assertNotNull(result);
    }

    @Test
    public void getProductReviewsForAdmin() {
        ProductReviewDto productReviewDto = ProductObjectProvider.createProductReviewDto();
        Product mockProduct = ProductObjectProvider.createProduct();
        when(productRepository.findById(id)).thenReturn(Optional.of(mockProduct));
        when(productReviewMapper.toDto(mockProduct)).thenReturn(productReviewDto);
        ProductReviewDto result = productReviewService.getProductReviewsForAdmin(id);
        assertNotNull(result);
    }

    //@Test
    public void addComment() {
        ProductCommentDto productCommentDto = ProductObjectProvider.createProductCommentDto();
        Product mockProduct = ProductObjectProvider.createProduct();
        mockProduct.setReviews(Collections.singletonList(ProductObjectProvider.createReview()));
        Review mockReview = ProductObjectProvider.createReview();
        when(productRepository.findById(productCommentDto.getProductId())).thenReturn(Optional.of(mockProduct));
        doNothing().when(productValidator).validateBeforeComment(mockProduct, productCommentDto);
        when(reviewRepository.save(mockReview)).thenReturn(mockReview);
        when(reviewMapper.toDto(mockReview)).thenReturn(ProductObjectProvider.createReviewDto());
        ProductReviewDto result = productReviewService.addComment(ProductObjectProvider.createProductCommentDto());
        assertNotNull(result.getReviews());
    }

    @Test(expected = CommentDisabledException.class)
    public void add_comment_when_comment_is_disabled() {
        ProductCommentDto productCommentDto = ProductObjectProvider.createProductCommentDto();
        Product mockProduct = ProductObjectProvider.createProduct();
        when(productRepository.findById(productCommentDto.getProductId())).thenReturn(Optional.of(mockProduct));
        doThrow(new CommentDisabledException()).when(productValidator).validateBeforeComment(mockProduct, productCommentDto);
        productReviewService.addComment(ProductObjectProvider.createProductCommentDto());
    }

    @Test(expected = AccessDeniedException.class)
    public void add_comment_when_access_is_denied() {
        ProductCommentDto productCommentDto = ProductObjectProvider.createProductCommentDto();
        Product mockProduct = ProductObjectProvider.createProduct();
        when(productRepository.findById(productCommentDto.getProductId())).thenReturn(Optional.of(mockProduct));
        doThrow(new AccessDeniedException()).when(productValidator).validateBeforeComment(mockProduct, productCommentDto);
        productReviewService.addComment(ProductObjectProvider.createProductCommentDto());
    }

    @Test
    public void approveComment() {
        Review review = ProductObjectProvider.createReview();
        review.setProduct(ProductObjectProvider.createProduct());
        when(reviewRepository.findById(any())).thenReturn(Optional.of(review));
        when(reviewMapper.toDto(review)).thenReturn(ProductObjectProvider.createReviewDto());
        productReviewService.approveComment(id, true);
    }

    @Test(expected = ReviewNotFoundException.class)
    public void approve_comment_when_review_not_found() {
        when(reviewRepository.findById(any())).thenThrow(new ReviewNotFoundException());
        productReviewService.approveComment(id, true);
    }

}
