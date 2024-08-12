package me.project.alphaapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_order")
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_order")
    private Long customerOrderId;

    @ManyToOne
    @JoinColumn(name = "customer_custo", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "address_addr")
    private Address address;

    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}
