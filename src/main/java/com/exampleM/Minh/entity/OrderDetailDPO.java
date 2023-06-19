package com.exampleM.Minh.entity;

import lombok.Data;

@Data
public class OrderDetailDPO {
    private Long Id;
    private Long Order_Id;
    private Long ProductId;
    private Integer Quantity;
    private Double Sum;
}
