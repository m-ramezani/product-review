package com.myshop.productreview.unit.controller;

import com.myshop.productreview.dto.CreateProductDto;
import com.myshop.productreview.dto.ProductDto;
import com.myshop.productreview.dto.ProductOrderDto;
import com.myshop.productreview.enums.ProductType;
import com.myshop.productreview.search.AdminProductSearchCriteria;
import com.myshop.productreview.search.UserProductSearchCriteria;
import com.myshop.productreview.service.ProductServiceImpl;
import com.myshop.productreview.unit.ProductObjectProvider;
import com.myshop.productreview.web.rest.ProductController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductServiceImpl productService;

    private int page;
    private int size;
    private int totalElements;
    private PageRequest pageable;


    @Before
    public void setup() {
        page = 1;
        size = 10;
        totalElements = 100;
        pageable = PageRequest.of(page, size);
    }

    @Test
    public void create() {
        CreateProductDto createProductDto = ProductObjectProvider.createNewProductDto();
        ProductDto productDto = ProductObjectProvider.createProductDto();
        when(productService.create(createProductDto)).thenReturn(productDto);
        ProductDto result = productController.create("authorization", createProductDto).getBody();
        assertEquals(Objects.requireNonNull(result).getName(), createProductDto.getName());
    }

    @Test
    public void update() {
        ProductDto productDto = ProductObjectProvider.createProductDto();
        String productName = productDto.getName();
        when(productService.update(productDto)).thenReturn(productDto);
        ProductDto result = productController.update("authorization", productDto).getBody();
        assertEquals(Objects.requireNonNull(result).getName(), productName);
    }

    @Test
    public void getProductsForUser() {
        ProductDto productDto = ProductObjectProvider.createProductDto();
        Page<ProductDto> productDtoPage = new PageImpl<>(Collections.singletonList(productDto), pageable, totalElements);
        UserProductSearchCriteria searchCriteria = UserProductSearchCriteria.of()
                .pageRequest(PageRequest.of(page, size))
                .build();
        when(productService.getProductsForUser(searchCriteria)).thenReturn(productDtoPage);
        Page<ProductDto> result = productController.getProductsForUser(page, size, ProductType.ACCESSORIES, 1L, 1L).getBody();
    }

    @Test
    public void getProductsForAdmin() {
        ProductDto productDto = ProductObjectProvider.createProductDto();
        Page<ProductDto> productDtoPage = new PageImpl<>(Collections.singletonList(productDto), pageable, totalElements);
        AdminProductSearchCriteria searchCriteria = AdminProductSearchCriteria.of()
                .pageRequest(PageRequest.of(page, size))
                .build();
        when(productService.getProductsForAdmin(searchCriteria)).thenReturn(productDtoPage);
        Page<ProductDto> result = productController.getProductsForAdmin("authorization", page, size, false, false, false).getBody();
    }

    @Test
    public void getProduct() {
        ProductDto productDto = ProductObjectProvider.createProductDto();
        when(productService.getProduct(1L)).thenReturn(productDto);
        ProductDto result = productController.getProduct(1L).getBody();
        assertNotNull(Objects.requireNonNull(result).getName());
    }

    @Test
    public void orderProduct() {
        ProductOrderDto orderDto = ProductOrderDto.of()
                .userId(1L)
                .productId(1L)
                .build();
        doNothing().when(productService).orderProduct(orderDto);
        HttpStatus status = productController.orderProduct("authorization", orderDto).getStatusCode();
        assertEquals(status, HttpStatus.OK);
    }

}
