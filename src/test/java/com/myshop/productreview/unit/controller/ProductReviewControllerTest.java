package com.myshop.productreview.unit.controller;

import com.myshop.productreview.dto.ProductReviewDto;
import com.myshop.productreview.service.ProductReviewServiceImpl;
import com.myshop.productreview.unit.ProductObjectProvider;
import com.myshop.productreview.web.rest.ProductReviewController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductReviewControllerTest {
    @InjectMocks
    private ProductReviewController productReviewController;

    @Mock
    private ProductReviewServiceImpl productReviewService;

    private Long id;

    @Before
    public void setup() {
        id = 1L;
    }

    @Test
    public void getProductReviewsForUser() {
        ProductReviewDto productReviewDto = ProductObjectProvider.createProductReviewDto();
        when(productReviewService.getProductReviewsForUser(id)).thenReturn(productReviewDto);
        ProductReviewDto result = productReviewController.getProductReviewsForUser(id).getBody();
        assertNotNull(result);
    }

    @Test
    public void getProductReviewsForAdmin() {
        ProductReviewDto productReviewDto = ProductObjectProvider.createProductReviewDto();
        when(productReviewService.getProductReviewsForAdmin(id)).thenReturn(productReviewDto);
        ProductReviewDto result = productReviewController.getProductReviewsForAdmin("authorization", id).getBody();
        assertNotNull(result);
    }

    @Test
    public void addComment() {
        ProductReviewDto productReviewDto = ProductObjectProvider.createProductReviewDto();
        when(productReviewService.addComment(ProductObjectProvider.createProductCommentDto())).thenReturn(productReviewDto);
        ProductReviewDto result = productReviewController.addComment("authorization", ProductObjectProvider.createProductCommentDto()).getBody();
        assertNotNull(Objects.requireNonNull(result).getReviews());
    }

    @Test
    public void approveComment() {
        HttpStatus status = productReviewController.approveComment("authorization", id, true).getStatusCode();
        assertEquals(status, HttpStatus.OK);
    }
}
