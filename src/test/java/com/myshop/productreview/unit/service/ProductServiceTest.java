package com.myshop.productreview.unit.service;

import com.myshop.productreview.dto.CreateProductDto;
import com.myshop.productreview.dto.ProductDto;
import com.myshop.productreview.dto.ProductOrderDto;
import com.myshop.productreview.exception.ProductNotFoundException;
import com.myshop.productreview.exception.UserNotFoundException;
import com.myshop.productreview.mapper.ProductMapper;
import com.myshop.productreview.model.Product;
import com.myshop.productreview.model.User;
import com.myshop.productreview.repository.ProductRepository;
import com.myshop.productreview.repository.UserRepository;
import com.myshop.productreview.search.AdminProductSearchCriteria;
import com.myshop.productreview.search.UserProductSearchCriteria;
import com.myshop.productreview.service.ProductServiceImpl;
import com.myshop.productreview.unit.ProductObjectProvider;
import com.myshop.productreview.unit.UserObjectProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    private PageRequest pageable;
    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserRepository userRepository;


    private int totalElements;
    private Long id;
    private int page;
    private int size;

    @Before
    public void setup() {
        id = 1L;
        totalElements = 100;
        page = 1;
        size = 10;
        pageable = PageRequest.of(page, size);
    }

    @Test
    public void create() {
        CreateProductDto createProductDto = ProductObjectProvider.createNewProductDto();
        ProductDto productDto = ProductObjectProvider.createProductDto();
        Product mockProduct = ProductObjectProvider.createProduct();
        when(productMapper.toEntity(createProductDto)).thenReturn(mockProduct);
        when(productMapper.toDto(mockProduct)).thenReturn(productDto);
        ProductDto result = productService.create(createProductDto);
        assertEquals(result.getName(), createProductDto.getName());
    }

    @Test
    public void update() {
        ProductDto productDto = ProductObjectProvider.createProductDto();
        String productName = productDto.getName();
        Product mockProduct = ProductObjectProvider.createProduct();
        when(productRepository.findById(productDto.getId())).thenReturn(Optional.of(mockProduct));
        when(productMapper.toEntity(productDto)).thenReturn(mockProduct);
        ProductDto result = productService.update(productDto);
        assertEquals(result.getName(), productName);
    }

    @Test(expected = ProductNotFoundException.class)
    public void update_when_not_found() {
        ProductDto productDto = ProductObjectProvider.createProductDto();
        when(productRepository.findById(productDto.getId())).thenThrow(new ProductNotFoundException());
        productService.update(productDto);
    }

    @Test
    public void getProductsForUser() {
        UserProductSearchCriteria searchCriteria = UserProductSearchCriteria.of()
                .pageRequest(PageRequest.of(page, size))
                .build();
        ProductDto productDto = ProductObjectProvider.createProductDto();
        Page<Product> products = new PageImpl<>(Collections.singletonList(ProductObjectProvider.createProduct()), pageable, totalElements);
        when(productRepository.searchForUser(searchCriteria.getType(), searchCriteria.getMinPrice(),
                searchCriteria.getMaxPrice(), pageable)).thenReturn(products);
        when(productMapper.toDto(products.toList())).thenReturn(Collections.singletonList(productDto));
        Page<ProductDto> result = productService.getProductsForUser(searchCriteria);
        assertNotNull(result.getContent());
    }

    @Test
    public void getProductsForAdmin() {
        AdminProductSearchCriteria searchCriteria = AdminProductSearchCriteria.of()
                .pageRequest(PageRequest.of(page, size))
                .build();
        ProductDto productDto = ProductObjectProvider.createProductDto();
        Page<Product> products = new PageImpl<>(Collections.singletonList(ProductObjectProvider.createProduct()), pageable, totalElements);
        when(productRepository.searchForAdmin(searchCriteria.getDisplayEnabled(), searchCriteria.getCommentEnabled(),
                searchCriteria.getCommentEnabledForAllUsers(), pageable)).thenReturn(products);
        when(productMapper.toDto(products.toList())).thenReturn(Collections.singletonList(productDto));
        Page<ProductDto> result = productService.getProductsForAdmin(searchCriteria);
        assertNotNull(result.getContent());
    }

    @Test
    public void getProduct() {
        ProductDto productDto = ProductObjectProvider.createProductDto();
        Product mockProduct = ProductObjectProvider.createProduct();
        when(productRepository.findById(id)).thenReturn(Optional.of(mockProduct));
        when(productMapper.toDto(mockProduct)).thenReturn(productDto);
        ProductDto result = productService.getProduct(id);
        assertNotNull(result);
    }

    @Test(expected = ProductNotFoundException.class)
    public void get_product_when_not_found() {
        when(productRepository.findById(id)).thenThrow(new ProductNotFoundException());
        productService.getProduct(id);
    }

    //@Test
    public void orderProduct() {
        ProductOrderDto orderDto = ProductOrderDto.of()
                .userId(id)
                .productId(id)
                .build();
        User mockUser = UserObjectProvider.createUser();
        Product mockProduct = ProductObjectProvider.createProduct();
        mockUser.setProducts(Collections.singletonList(mockProduct));
        when(userRepository.findById(orderDto.getUserId())).thenReturn(Optional.of(mockUser));
        when(productRepository.findById(orderDto.getProductId())).thenReturn(Optional.of(mockProduct));
        productService.orderProduct(orderDto);
        verify(productService, atLeastOnce()).orderProduct(any());
    }

    @Test(expected = UserNotFoundException.class)
    public void order_product_when_user_not_found() {
        ProductOrderDto orderDto = ProductOrderDto.of()
                .userId(id)
                .productId(id)
                .build();
        when(userRepository.findById(orderDto.getUserId())).thenThrow(new UserNotFoundException());
        productService.orderProduct(orderDto);
    }

    @Test(expected = ProductNotFoundException.class)
    public void order_product_when_product_not_found() {
        ProductOrderDto orderDto = ProductOrderDto.of()
                .userId(id)
                .productId(id)
                .build();
        User mockUser = UserObjectProvider.createUser();

        when(userRepository.findById(orderDto.getUserId())).thenReturn(Optional.of(mockUser));
        when(productRepository.findById(orderDto.getProductId())).thenThrow(new ProductNotFoundException());
        productService.orderProduct(orderDto);
    }

}
