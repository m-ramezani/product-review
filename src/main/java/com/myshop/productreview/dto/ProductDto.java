package com.myshop.productreview.dto;

import com.myshop.productreview.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "of")
public class ProductDto implements Serializable {

    private Long id;

    private String name;

    private long price;

    private ProductType type;

    private boolean commentEnabled;

    private boolean displayEnabled;

    private boolean commentEnabledForAllUsers;

}
