package com.exampleM.Minh.DTO;

import com.exampleM.Minh.entity.Category;
import com.exampleM.Minh.entity.Order;

import lombok.Data;

@Data
public class OrderDetailDTO {
    private long id;
    private Order order;
    private long Productid;
    private String Title;
    private Double Price;
    private String image;
    private Category category;
    private long Quantity;
    private long Sum;
}