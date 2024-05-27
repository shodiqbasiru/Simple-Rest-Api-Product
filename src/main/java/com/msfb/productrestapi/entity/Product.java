package com.msfb.productrestapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "m_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "sku_code", nullable = false, length = 50)
    private String skuCode;

    @Column(name = "product_name", nullable = false, length = 50)
    private String productName;

    @Column(name = "qty", nullable = false)
    private Integer qty;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "image_id", unique = true)
    private Image product;
}
