package com.myshop.productreview.web.rest;


import com.myshop.productreview.dto.ProductCommentDto;
import com.myshop.productreview.dto.ProductReviewDto;
import com.myshop.productreview.dto.ReviewDto;
import com.myshop.productreview.service.ProductReviewService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product-review")
@SuppressWarnings(value = "unused")
public class ProductReviewController {
    private final ProductReviewService productReviewService;

    public ProductReviewController(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }

    @GetMapping("/{productId}/user")
    public ResponseEntity<ProductReviewDto> getProductReviewsForUser(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(productReviewService.getProductReviewsForUser(productId));
    }

    @GetMapping("/{productId}/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ProductReviewDto> getProductReviewsForAdmin(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                                                      @PathVariable("productId") Long productId) {
        return ResponseEntity.ok(productReviewService.getProductReviewsForAdmin(productId));
    }

    @PostMapping("/comment")
    public ResponseEntity<ProductReviewDto> addComment(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                                       @RequestBody @Valid ProductCommentDto productCommentDto) {
        return ResponseEntity.ok(productReviewService.addComment(productCommentDto));
    }

    @PutMapping("/approve-comment")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ReviewDto> approveComment(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                                    @RequestParam("reviewId") Long reviewId,
                                                    @RequestParam("approved") boolean approved) {
        ReviewDto reviewDto = productReviewService.approveComment(reviewId, approved);
        return ResponseEntity.ok(reviewDto);
    }

}
