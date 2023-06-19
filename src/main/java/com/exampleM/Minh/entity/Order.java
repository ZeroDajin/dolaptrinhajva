package com.exampleM.Minh.entity;

import java.util.List;

import com.exampleM.Minh.validator.annotation.ValidCategoryId;
import com.exampleM.Minh.validator.annotation.ValidUserId;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name="order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sumprice")
    private Double SumPrice;

    @Column(name ="psQuantity")
    private Integer PsQuantity;

    private List<OrderDetail> orderDetail;
    
    @Column(name="status")
    private Boolean Status;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    @ValidUserId
    private User user;
}