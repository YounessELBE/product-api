package com.alten.product.repository.entity;

import com.alten.product.enums.InventoryStatus;
import com.alten.product.repository.entity.shared.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Product extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_code", unique = true, nullable = false)
    private String code;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "product_description", nullable = false, length = 1000)
    private String description;

    @Column(name = "product_image")
    private String image;

    @Column(name = "product_category", nullable = false)
    private String category;

    @Column(name = "product_price", nullable = false)
    private Double price;

    @Column(name = "product_quantity", nullable = false)
    private Integer quantity;

    @Column(name = "internal_reference", nullable = false, unique = true)
    private String internalReference;

    @Column(name = "shell_id")
    private Long shellId;

    @Enumerated(EnumType.STRING)
    @Column(name = "inventory_status", nullable = false)
    private InventoryStatus inventoryStatus;

    @Column(name = "product_rating")
    private Integer rating;
}
