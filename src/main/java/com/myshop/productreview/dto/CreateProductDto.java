package com.myshop.productreview.dto;

import com.myshop.productreview.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "of")
public class CreateProductDto implements Serializable {
    @NotEmpty
    private String name;

    @NotNull
    private long price;

    @NotNull
    private ProductType type;

    @NotNull
    private boolean commentEnabled;

    @NotNull
    private boolean commentEnabledForAllUsers;

    @NotNull
    private boolean displayEnabled;

}
