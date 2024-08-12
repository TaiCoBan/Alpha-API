package me.project.alphaapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

//    @Email(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
    @Email(message = "Please provide a valid email address")
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

//    @Column(name = "address")
//    private String address;
    @ManyToOne
    @JoinColumn(name = "address")
    private Address address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "roles", nullable = false)
    private String roles = "USER";

    @OneToMany(mappedBy = "customer")
    private List<CustomerOrder> customerOrders;
}
