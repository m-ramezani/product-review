package com.myshop.productreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "of")
public class ProductCommentDto implements Serializable {
    @NotNull
    private Long productId;

    @NotNull
    private Long userId;

    @NotEmpty
    private String comment;

    @NotNull
    @Max(10)
    @Min(1)
    private double vote;
}
