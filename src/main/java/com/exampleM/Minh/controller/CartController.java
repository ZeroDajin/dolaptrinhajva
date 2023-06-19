package com.exampleM.Minh.controller;


import com.exampleM.Minh.global.GlobalData;
import com.exampleM.Minh.services.OrderDetailService;
import com.exampleM.Minh.services.OrderService;
import com.exampleM.Minh.services.ProductService;
import com.exampleM.Minh.services.UserService;

import jakarta.persistence.Id;

import com.exampleM.Minh.DTO.OrderDetailDTO;
import com.exampleM.Minh.entity.Order;
import com.exampleM.Minh.entity.OrderDetail;
import com.exampleM.Minh.entity.Product;

import java.util.List;
import java.util.Random;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @GetMapping("/cart")
    public String cartGet(Model model){
        Double sum = 0.0;
        Double sumQuantity =0.0;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Order findDebt = orderService.GetPendingOrder(currentPrincipalName);
        
        for (OrderDetail find : orderDetailService.getAllOrderDetailByOrderId(findDebt.getId())) {
            sum += find.getPrice() * find.getQuantity();
            sumQuantity+=find.getQuantity();
        }
        model.addAttribute("cartCount",sumQuantity);
        model.addAttribute("total",sum);
        model.addAttribute("cart",orderDetailService.getAllOrderDetailByOrderId(findDebt.getId()));
        return "cart";
    }//page cart

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Order findDebt = orderService.GetPendingOrder(currentPrincipalName);
        Product Ptemp = productService.getProductById(id).get();
        if(findDebt !=null)
        {
        for (OrderDetail checker : orderDetailService.getAllOrderDetailByOrderId(findDebt.getId()))
        {
            if(checker.getProductid()==id)
            {
                checker.setQuantity(checker.getQuantity()+1);
                orderDetailService.updateOrderDetail(checker);
                break;
            }
            else
            {
                OrderDetail ODtemp = new OrderDetail();
                //ODtemp.setCategory(Ptemp.getCategory());
                ODtemp.setOrder(findDebt);
                ODtemp.setImage(Ptemp.getImage());
                ODtemp.setPrice(Ptemp.getPrice());
                ODtemp.setProductid(id);
                ODtemp.setTitle(Ptemp.getTitle());
                ODtemp.setQuantity(1);
                ODtemp.setSum(ODtemp.getPrice()*ODtemp.getQuantity());
                orderDetailService.addOrderDetail(ODtemp);
                break;
            }
        }
    }
        else
        {
            Order createDebt = new Order();
            createDebt.setUser(userService.FindUserByUsername(currentPrincipalName));
            createDebt.setStatus(0);
            orderService.addOrder(createDebt);
            OrderDetail ODtemp = new OrderDetail();
                //ODtemp.setCategory(Ptemp.getCategory());
                ODtemp.setOrder(createDebt);
                ODtemp.setImage(Ptemp.getImage());
                ODtemp.setPrice(Ptemp.getPrice());
                ODtemp.setProductid(id);
                ODtemp.setTitle(Ptemp.getTitle());
                ODtemp.setQuantity(1);
                ODtemp.setSum(ODtemp.getPrice()*ODtemp.getQuantity());
                orderDetailService.addOrderDetail(ODtemp);
        }
        return "redirect:/shop";
    }//click add from page viewProduct

    @GetMapping("/cart/removeItem/{id}")
    public String cartItemRemove(@PathVariable Long id){
        orderDetailService.deleteOrderDetail(id);
        return "redirect:/cart";
    } // delete 1 product

    @GetMapping("/checkout")
    public String checkout(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Order updatefinal = orderService.GetPendingOrder(currentPrincipalName);
        updatefinal.setPsQuantity(updatefinal.getOrderDetail().size());
        Double sum=0.0;
        for(OrderDetail temp : orderDetailService.getAllOrderDetailByOrderId(updatefinal.getId()))
        {
            sum+= temp.getSum();
        }
        updatefinal.setSumPrice(sum);
        orderService.updateOrder(updatefinal);
        model.addAttribute("cartCount",updatefinal.getPsQuantity());
        model.addAttribute("total",updatefinal.getSumPrice());
        //model.addAttribute("cart", GlobalData.cart);
        return "checkout";
    } // checkout totalPrice
}