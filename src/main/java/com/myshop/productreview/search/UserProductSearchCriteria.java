package com.myshop.productreview.search;

import com.myshop.productreview.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "of")
public class UserProductSearchCriteria {
    private ProductType type;
    private Long minPrice;
    private Long maxPrice;
    private PageRequest pageRequest;
}
