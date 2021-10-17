package com.myshop.productreview.mapper;

import com.myshop.productreview.dto.CreateProductDto;
import com.myshop.productreview.dto.ProductDto;
import com.myshop.productreview.dto.ProductDto.ProductDtoBuilder;
import com.myshop.productreview.model.Product;
import com.myshop.productreview.model.Product.ProductBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-17T16:23:30+0330",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Ubuntu)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(ProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        ProductBuilder product = Product.of();

        product.id( dto.getId() );
        product.name( dto.getName() );
        product.price( dto.getPrice() );
        product.type( dto.getType() );
        product.commentEnabled( dto.isCommentEnabled() );
        product.commentEnabledForAllUsers( dto.isCommentEnabledForAllUsers() );
        product.displayEnabled( dto.isDisplayEnabled() );

        return product.build();
    }

    @Override
    public ProductDto toDto(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductDtoBuilder productDto = ProductDto.of();

        productDto.id( entity.getId() );
        productDto.name( entity.getName() );
        productDto.price( entity.getPrice() );
        productDto.type( entity.getType() );
        productDto.commentEnabled( entity.isCommentEnabled() );
        productDto.displayEnabled( entity.isDisplayEnabled() );
        productDto.commentEnabledForAllUsers( entity.isCommentEnabledForAllUsers() );

        return productDto.build();
    }

    @Override
    public List<Product> toEntity(List<ProductDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( dtoList.size() );
        for ( ProductDto productDto : dtoList ) {
            list.add( toEntity( productDto ) );
        }

        return list;
    }

    @Override
    public List<ProductDto> toDto(List<Product> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>( entityList.size() );
        for ( Product product : entityList ) {
            list.add( toDto( product ) );
        }

        return list;
    }

    @Override
    public Product toEntity(CreateProductDto createProductDto) {
        if ( createProductDto == null ) {
            return null;
        }

        ProductBuilder product = Product.of();

        product.name( createProductDto.getName() );
        product.price( createProductDto.getPrice() );
        product.type( createProductDto.getType() );
        product.commentEnabled( createProductDto.isCommentEnabled() );
        product.commentEnabledForAllUsers( createProductDto.isCommentEnabledForAllUsers() );
        product.displayEnabled( createProductDto.isDisplayEnabled() );

        return product.build();
    }
}
