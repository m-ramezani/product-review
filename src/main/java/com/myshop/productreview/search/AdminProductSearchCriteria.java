package com.myshop.productreview.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "of")
public class AdminProductSearchCriteria {
    private PageRequest pageRequest;
    private Boolean displayEnabled;
    private Boolean commentEnabled;
    private Boolean commentEnabledForAllUsers;

}
