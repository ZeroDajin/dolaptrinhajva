package com.exampleM.Minh.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.exampleM.Minh.global.GlobalData;
import com.exampleM.Minh.services.ProductService;
import com.exampleM.Minh.services.CategoryService;

import java.util.NoSuchElementException;

@Controller
public class HomeUserController {
        @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;
    @GetMapping({"/", "/home"})
    public String home(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "index";
    } //index
        @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.getAllProducts());
        return "shop";
    }
        @GetMapping("/shop/category/{id}")
    public String shopByCat(@PathVariable long id, Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.getAllProductByCategoryId(id));
        return "shop";

    }
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(@PathVariable long id, Model model){
        
        model.addAttribute("cartCount", GlobalData.cart.size());       
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.getProductById(id));
        if(productService.getProductById(id)==null){
            throw new NoSuchElementException("No value present");
        }
        return "viewProduct";
        // view product Details
    }
}



