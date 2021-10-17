package com.myshop.productreview.dto;

import com.myshop.productreview.enums.ProductType;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ProductReviewDto extends ProductDto {

    private List<ReviewDto> reviews;

    private double averageVote;

    private int totalVote;

    @Builder
    public ProductReviewDto(Long id, String name, long price, ProductType type, boolean commentEnabled, boolean displayEnabled,
                            boolean commentEnabledForAllUsers, List<ReviewDto> reviews, double averageVote, int totalVote) {
        super(id, name, price, type, commentEnabled, displayEnabled, commentEnabledForAllUsers);
        this.reviews = reviews;
        this.averageVote = averageVote;
        this.totalVote = totalVote;
    }

    public ProductReviewDto(List<ReviewDto> reviews, double averageVote, int totalVote) {
        this.reviews = reviews;
        this.averageVote = averageVote;
        this.totalVote = totalVote;
    }
}
