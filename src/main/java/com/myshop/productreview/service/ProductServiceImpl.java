package com.myshop.productreview.service;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository, UserRepository userRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ProductDto create(CreateProductDto createProductDto) {
        Product product = productMapper.toEntity(createProductDto);
        productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public ProductDto update(ProductDto productDto) {
        productRepository.findById(productDto.getId()).orElseThrow(ProductNotFoundException::new);
        Product product = productMapper.toEntity(productDto);
        productRepository.save(product);
        return productDto;
    }

    @Override
    public Page<ProductDto> getProductsForUser(UserProductSearchCriteria searchCriteria) {
        Page<Product> products = productRepository.searchForUser(searchCriteria.getType(), searchCriteria.getMinPrice(),
                searchCriteria.getMaxPrice(), searchCriteria.getPageRequest());
        List<ProductDto> productDtoList = productMapper.toDto(products.toList());
        Page<ProductDto> productDtoPage = new PageImpl<>(productDtoList, searchCriteria.getPageRequest(), products.getTotalElements());
        return productDtoPage;
    }

    @Override
    public Page<ProductDto> getProductsForAdmin(AdminProductSearchCriteria searchCriteria) {
        Page<Product> products = productRepository.searchForAdmin(searchCriteria.getDisplayEnabled(), searchCriteria.getCommentEnabled(),
                searchCriteria.getCommentEnabledForAllUsers(), searchCriteria.getPageRequest());
        List<ProductDto> productDtoList = productMapper.toDto(products.toList());
        Page<ProductDto> productDtoPage = new PageImpl<>(productDtoList, searchCriteria.getPageRequest(), products.getTotalElements());
        return productDtoPage;
    }

    @Override
    public ProductDto getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        return productMapper.toDto(product);
    }

    @Override
    public void orderProduct(ProductOrderDto productOrderDto) {
        User user = userRepository.findById(productOrderDto.getUserId())
                .orElseThrow(UserNotFoundException::new);
        Product product = productRepository.findById(productOrderDto.getProductId())
                .orElseThrow(ProductNotFoundException::new);
        user.getProducts().add(product);
    }

}
