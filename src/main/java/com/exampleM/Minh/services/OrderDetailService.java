package com.exampleM.Minh.services;

import com.exampleM.Minh.entity.OrderDetail;
import com.exampleM.Minh.repository.IOrderDetailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public class OrderDetailService {
    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    public List<OrderDetail> getAllOrderDetails(){
        return orderDetailRepository.findAll();
    }


    public Optional<OrderDetail> getOrderDetailById(Long id){
        return orderDetailRepository.findById(id);
    }

        
        public void addOrderDetail(OrderDetail orderDetail){
            orderDetailRepository.save(orderDetail);
        }
        public void updateOrderDetail(OrderDetail orderDetail){
            orderDetailRepository.save(orderDetail);
        }
        public void deleteOrderDetail(Long id){
            orderDetailRepository.deleteById(id);
        }
	public List<OrderDetail> getAllOrderDetailByOrderId(long id) {
        return orderDetailRepository.findAllByOrderId(id);
    }
}
