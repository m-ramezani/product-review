package com.myshop.productreview.web.rest;


import com.myshop.productreview.dto.CreateProductDto;
import com.myshop.productreview.dto.ProductDto;
import com.myshop.productreview.dto.ProductOrderDto;
import com.myshop.productreview.enums.ProductType;
import com.myshop.productreview.search.AdminProductSearchCriteria;
import com.myshop.productreview.search.UserProductSearchCriteria;
import com.myshop.productreview.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product")
@SuppressWarnings(value = "unused")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ProductDto> create(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                             @RequestBody @Valid CreateProductDto createProductDto) {
        return ResponseEntity.ok(productService.create(createProductDto));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ProductDto> update(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                             @RequestBody @Valid ProductDto productDto) {
        return ResponseEntity.ok(productService.update(productDto));
    }

    @GetMapping("/list/user")
    public ResponseEntity<Page<ProductDto>> getProductsForUser(@RequestParam("page") Integer page,
                                                               @RequestParam("size") Integer size,
                                                               @RequestParam(value = "type", required = false) ProductType type,
                                                               @RequestParam(value = "minPrice", required = false) Long minPrice,
                                                               @RequestParam(value = "maxPrice", required = false) Long maxPrice) {

        UserProductSearchCriteria searchCriteria = UserProductSearchCriteria.of()
                .type(type)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .pageRequest(PageRequest.of(page, size))
                .build();
        return ResponseEntity.ok(productService.getProductsForUser(searchCriteria));
    }

    @GetMapping("/list/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page<ProductDto>> getProductsForAdmin(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                                                @RequestParam("page") Integer page,
                                                                @RequestParam("size") Integer size,
                                                                @RequestParam(value = "displayEnabled", required = false) Boolean displayEnabled,
                                                                @RequestParam(value = "commentEnabled", required = false) Boolean commentEnabled,
                                                                @RequestParam(value = "commentEnabledForAllUsers", required = false) Boolean commentEnabledForAllUsers) {

        AdminProductSearchCriteria searchCriteria = AdminProductSearchCriteria.of()
                .commentEnabled(commentEnabled)
                .displayEnabled(displayEnabled)
                .commentEnabledForAllUsers(commentEnabledForAllUsers)
                .pageRequest(PageRequest.of(page, size))
                .build();
        return ResponseEntity.ok(productService.getProductsForAdmin(searchCriteria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PostMapping("/order-product")
    public ResponseEntity<HttpStatus> orderProduct(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                                   @RequestBody @Valid ProductOrderDto productOrderDto) {
        productService.orderProduct(productOrderDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
