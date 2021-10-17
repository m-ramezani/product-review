package com.myshop.productreview.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "of")
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "vote")
    private double vote;

    @Column(name = "posted_at")
    private LocalDateTime postedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "approved")
    private boolean approved;

    @PostConstruct
    private void initialize() {
        this.postedAt = LocalDateTime.now();
    }

}
