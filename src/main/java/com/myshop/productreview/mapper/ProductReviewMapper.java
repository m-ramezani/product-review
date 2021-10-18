package com.myshop.productreview.mapper;

import com.myshop.productreview.dto.ProductReviewDto;
import com.myshop.productreview.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ReviewMapper.class})
public interface ProductReviewMapper {
    List<ProductReviewDto> toProductReviewDtos(List<Product> products);

    ProductReviewDto toDto(Product product);
}
