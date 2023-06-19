package com.exampleM.Minh.repository;

import com.exampleM.Minh.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {
    @Query(value ="SELECT * FROM orders WHERE status=0 AND user_id=?1",nativeQuery = true)
    Order FindByStatusAndUserId(Long user_id);
}
