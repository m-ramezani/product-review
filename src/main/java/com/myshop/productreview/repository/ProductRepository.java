package com.myshop.productreview.repository;


import com.myshop.productreview.enums.ProductType;
import com.myshop.productreview.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p " +
            "where (:type is null or p.type = :type) " +
            "and (:minPrice is null or p.price >= :minPrice) " +
            "and (:maxPrice is null or p.price <= :maxPrice)" +
            "and p.displayEnabled = true " +
            "order by p.createdAt")
    Page<Product> searchForUser(@Param("type") ProductType type,
                         @Param("minPrice") Long minPrice,
                         @Param("maxPrice") Long maxPrice,
                         Pageable pageable);

    @Query("select p from Product p " +
            "where (:displayEnabled is null or p.displayEnabled = :displayEnabled) " +
            "and (:commentEnabled is null or p.commentEnabled >= :commentEnabled) " +
            "and (:commentEnabledForAllUsers is null or p.commentEnabledForAllUsers <= :commentEnabledForAllUsers)" +
            "order by p.createdAt")
    Page<Product> searchForAdmin(@Param("displayEnabled") Boolean displayEnabled,
                         @Param("commentEnabled") Boolean commentEnabled,
                         @Param("commentEnabledForAllUsers") Boolean commentEnabledForAllUsers,
                         Pageable pageable);
}
