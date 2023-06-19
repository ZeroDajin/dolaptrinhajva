package com.exampleM.Minh.entity;

import java.util.List;

import com.exampleM.Minh.validator.annotation.ValidUserId;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sumprice")
    private Double SumPrice;

    @Column(name ="psquantity")
    private Integer PsQuantity;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetail;
    
    @Column(name="status")
    private Integer Status;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;
}