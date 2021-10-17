package com.myshop.productreview.mapper;

import com.myshop.productreview.dto.ProductReviewDto;
import com.myshop.productreview.dto.ProductReviewDto.ProductReviewDtoBuilder;
import com.myshop.productreview.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-17T16:23:30+0330",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Ubuntu)"
)
@Component
public class ProductReviewMapperImpl implements ProductReviewMapper {

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public List<ProductReviewDto> toProductReviewDtos(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductReviewDto> list = new ArrayList<ProductReviewDto>( products.size() );
        for ( Product product : products ) {
            list.add( toDto( product ) );
        }

        return list;
    }

    @Override
    public ProductReviewDto toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductReviewDtoBuilder productReviewDto = ProductReviewDto.builder();

        productReviewDto.id( product.getId() );
        productReviewDto.name( product.getName() );
        productReviewDto.price( product.getPrice() );
        productReviewDto.type( product.getType() );
        productReviewDto.commentEnabled( product.isCommentEnabled() );
        productReviewDto.displayEnabled( product.isDisplayEnabled() );
        productReviewDto.commentEnabledForAllUsers( product.isCommentEnabledForAllUsers() );
        productReviewDto.reviews( reviewMapper.toDto( product.getReviews() ) );
        productReviewDto.averageVote( product.getAverageVote() );
        productReviewDto.totalVote( product.getTotalVote() );

        return productReviewDto.build();
    }
}
