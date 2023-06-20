package com.exampleM.Minh.services;

import com.exampleM.Minh.entity.Order;
import com.exampleM.Minh.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private UserService userService;
    public List<Order> getAllOrders() { return orderRepository.findAll(); }
    public  Order getOrderById(Long id){
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()){
            return optionalOrder.get();
        }else {
            throw new RuntimeException("Order not found");
        }
    }
    public void addOrder(Order category){
        orderRepository.save(category);
    }
    public void updateOrder(Order category){
        orderRepository.save(category);
    }
    public  Order saveOrder(Order category){
        return orderRepository.save(category);
    }

    public void deleteOrder(Long id){orderRepository.deleteById(id);}
    public Order GetPendingOrder(String username)
    {
        Long find = userService.GetThatDebtByUsername(username).getId();
        return orderRepository.FindByStatusAndUserId(find);
    }
}
