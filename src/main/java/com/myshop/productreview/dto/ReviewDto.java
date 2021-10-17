package com.myshop.productreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "of")
public class ReviewDto implements Serializable {
   private Long id;

    private String comment;

    private double vote;

    boolean approved;
}
