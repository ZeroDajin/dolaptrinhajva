package com.exampleM.Minh.repository;

import com.exampleM.Minh.entity.OrderDetail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail,Long>{
    List<OrderDetail> findAllByOrderId(Long id);
}