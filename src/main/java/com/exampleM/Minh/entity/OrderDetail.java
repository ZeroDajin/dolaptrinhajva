package com.exampleM.Minh.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name="orderdetail")
public class OrderDetail{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="order_id",referencedColumnName = "id")
    private Order order;
    @Column(name="Productid")
    private Long Productid;
    @Column(name = "title")
    @NotEmpty(message = "not empty")
    @Size(max = 50,min =1,message = "title không hợp lệ")
    private String Title;
    @Column(name = "price")
    @NotNull(message = "giá cả không hợp lệ? NULL")
    private Double Price;
    @Column(name = "image")
    private String image;
    @Column(name = "category")
    @NotNull(message = "category không hợp lệ")
    private Category category;
    @Column(name = "quantity")
    @NotNull(message = "Số lượng không được null")
    private long Quantity;
    @Column(name = "sum")
    @NotNull(message = "tính tiền không được null")
    private Double Sum;
}
