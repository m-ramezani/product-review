package com.myshop.productreview.validator;

import com.myshop.productreview.dto.ProductCommentDto;
import com.myshop.productreview.exception.AccessDeniedException;
import com.myshop.productreview.exception.CommentDisabledException;
import com.myshop.productreview.exception.UserNotFoundException;
import com.myshop.productreview.model.Product;
import com.myshop.productreview.model.User;
import com.myshop.productreview.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {
    private final UserRepository userRepository;

    public ProductValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateBeforeComment(Product product, ProductCommentDto productCommentDto) {
        if (!product.isCommentEnabled()) {
            throw new CommentDisabledException("commenting is not enabled for this product");
        }

        User user = userRepository.findById(productCommentDto.getUserId())
                .orElseThrow(UserNotFoundException::new);

        if (!product.isCommentEnabledForAllUsers()) {
            if (!user.getProducts().contains(product)) {
                throw new AccessDeniedException("Only buyers can comment on this product");
            }
        }
    }
}
