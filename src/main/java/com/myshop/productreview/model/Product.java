package com.myshop.productreview.model;


import com.myshop.productreview.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "of")
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private long price;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private ProductType type;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @ManyToMany(mappedBy = "products")
    private List<User> users;

    @Column(name = "comment_enabled")
    private boolean commentEnabled;

    @Column(name = "enabled_for_all")
    private boolean commentEnabledForAllUsers;

    @Column(name = "total_vote")
    private int totalVote;

    @Column(name = "sum_vote")
    private double sumVote;

    @Column(name = "display_enabled")
    private boolean displayEnabled;

    @Transient
    private double averageVote;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PostLoad
    private void setAverageVote() {
        this.averageVote = (this.sumVote != 0 && this.totalVote != 0) ? (this.sumVote / this.totalVote) : 0;
    }

    @PostConstruct
    private void initialize() {
        this.createdAt = LocalDateTime.now();
    }

}
