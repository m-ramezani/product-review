package com.myshop.productreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "of")
public class ProductOrderDto implements Serializable {
    @NotNull
    private Long userId;

    @NotNull
    private Long productId;
}
