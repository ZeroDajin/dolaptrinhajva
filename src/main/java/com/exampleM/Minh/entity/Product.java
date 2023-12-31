package com.exampleM.Minh.entity;

import com.exampleM.Minh.validator.annotation.ValidCategoryId;
import com.exampleM.Minh.validator.annotation.ValidUserId;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    @NotEmpty(message = "not empty")
    @Size(max = 50,min =1,message = "ZZZZZZ")
    private String title;
    
    @Column(name = "price")
    @NotNull(message = "GGGGG")
    private Double price;
    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name="category_id",referencedColumnName = "id")
    @ValidCategoryId
    private Category category;
}