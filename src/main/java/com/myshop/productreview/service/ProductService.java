package com.myshop.productreview.service;

import com.myshop.productreview.dto.CreateProductDto;
import com.myshop.productreview.dto.ProductDto;
import com.myshop.productreview.dto.ProductOrderDto;
import com.myshop.productreview.search.AdminProductSearchCriteria;
import com.myshop.productreview.search.UserProductSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ProductService {
    ProductDto create(CreateProductDto createProductDto);

    ProductDto update(ProductDto productDto);

    Page<ProductDto> getProductsForUser(UserProductSearchCriteria searchCriteria);

    Page<ProductDto> getProductsForAdmin(AdminProductSearchCriteria searchCriteria);

    ProductDto getProduct(Long id);

    void orderProduct(ProductOrderDto productOrderDto);
}
