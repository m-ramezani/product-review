package com.myshop.productreview.repository;


import com.myshop.productreview.model.Product;
import com.myshop.productreview.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByProductAndApproved(Product product, boolean approved, Pageable pageable);
}
