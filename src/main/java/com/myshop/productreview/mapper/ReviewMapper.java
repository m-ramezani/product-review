package com.myshop.productreview.mapper;

import com.myshop.productreview.dto.ReviewDto;
import com.myshop.productreview.model.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper extends EntityMapper<ReviewDto, Review> {
}
