package me.project.alphaapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "name")
    private String name;

    @Column(name = "SKU")
    private String SKU;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private long price = 0;

    @Column(name = "stock")
    private int stock = 0;

    @ManyToOne
    @JoinColumn(name = "category_catego", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;
}
