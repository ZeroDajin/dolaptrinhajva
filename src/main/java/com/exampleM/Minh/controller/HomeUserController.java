package com.exampleM.Minh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.exampleM.Minh.global.GlobalData;
import com.exampleM.Minh.services.BookService;
import com.exampleM.Minh.services.CategoryService;

import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class HomeUserController {
        @Autowired
    CategoryService categoryService;

    @Autowired
    BookService bookService;
    @GetMapping({"/", "/home"})
    public String home(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "index";
    } //index
        @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("books", bookService.getAllBooks());
        return "shop";
    }
        @GetMapping("/shop/category/{id}")
    public String shopByCat(@PathVariable long id, Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("books", bookService.getAllBookByCategoryId(id));
        return "shop";

    }
     @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(@PathVariable long id, Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("books", bookService.getBookById(id));
        if(bookService.getBookById(id)==null){
            throw new NoSuchElementException("No value present");
        }
        return "viewProduct";
    }
// view product Details
}


