package com.exampleM.Minh.repository;

import com.exampleM.Minh.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {
    @Query("SELECT * FROM order WHERE status=0 AND user_id=?1")
    Order FindByStatusAndUserId(Long user_id);
}
