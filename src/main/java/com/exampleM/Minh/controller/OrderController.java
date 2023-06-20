package com.exampleM.Minh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exampleM.Minh.entity.Order;
import com.exampleM.Minh.entity.OrderDetail;
import com.exampleM.Minh.entity.Product;
import com.exampleM.Minh.services.OrderDetailService;
import com.exampleM.Minh.services.OrderService;

@Controller
@RequestMapping("/admin/orders")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;
    @GetMapping("/list")
    public String ShowAllOrders(Model model)
    {
        Double sumprice=0.0;
        List<Order> orders = orderService.getAllOrders();
        for(Order order:orders)
        {
            order.setPsQuantity(orderDetailService.getAllOrderDetailByOrderId(order.getId()).size());
            for(OrderDetail orderDetail : orderDetailService.getAllOrderDetailByOrderId(order.getId()))
            {
                sumprice +=orderDetail.getPrice()*orderDetail.getQuantity();
            }
            order.setSumPrice(sumprice);
            orderService.updateOrder(order);
        }
        List<Order> Afterorders = orderService.getAllOrders();
        model.addAttribute("orders",Afterorders);
        return "admin/order/list";
    }
    //@GetMapping("/detail/{id}")
    //public String 
}
