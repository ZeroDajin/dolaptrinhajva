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
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    @NotEmpty(message = "not empty")
    @Size(max = 50,min =1,message = "ZZZZZZ")
    private String title;
    @Column(name="author")
    private String author;
    @Column(name = "price")
    @NotNull(message = "GGGGG")
    private Double price;
    private String image;

    @ManyToOne
    @JoinColumn(name="category_id",referencedColumnName = "id")
    @ValidCategoryId
    private Category category;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    @ValidUserId
    private User user;
}