package com.myshop.productreview.mapper;

import com.myshop.productreview.dto.ReviewDto;
import com.myshop.productreview.model.Review;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-16T17:32:03+0330",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Ubuntu)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public Review toEntity(ReviewDto dto) {
        if ( dto == null ) {
            return null;
        }

        Review review = new Review();

        review.setId( dto.getId() );
        review.setComment( dto.getComment() );
        review.setVote( dto.getVote() );
        review.setApproved( dto.isApproved() );

        return review;
    }

    @Override
    public ReviewDto toDto(Review entity) {
        if ( entity == null ) {
            return null;
        }

        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId( entity.getId() );
        reviewDto.setComment( entity.getComment() );
        reviewDto.setVote( entity.getVote() );
        reviewDto.setApproved( entity.isApproved() );

        return reviewDto;
    }

    @Override
    public List<Review> toEntity(List<ReviewDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Review> list = new ArrayList<Review>( dtoList.size() );
        for ( ReviewDto reviewDto : dtoList ) {
            list.add( toEntity( reviewDto ) );
        }

        return list;
    }

    @Override
    public List<ReviewDto> toDto(List<Review> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ReviewDto> list = new ArrayList<ReviewDto>( entityList.size() );
        for ( Review review : entityList ) {
            list.add( toDto( review ) );
        }

        return list;
    }
}
