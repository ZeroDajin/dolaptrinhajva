package com.exampleM.Minh.repository;

import com.exampleM.Minh.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByCategory_Id(long id);
}
