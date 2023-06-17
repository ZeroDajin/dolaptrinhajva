package com.exampleM.Minh.controller;

import com.exampleM.Minh.entity.Book;
import com.exampleM.Minh.entity.Category;
import com.exampleM.Minh.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String showAllBooks(Model model){
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories",categories);
        return "admin/category/list";
    }
    @GetMapping("/add")
    public String addCategoryForm(Model model){
        model.addAttribute("category", new Category());

        return "admin/category/add";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute("book") Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }
    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable("id") Long id, Model model){
        Category editCategory = categoryService.getCategoryById(id);
        if (editCategory!=null){
            model.addAttribute("category", editCategory);

            return "admincategory/edit";
        } else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public  String editCategory( @ModelAttribute("book")Category uCategory,Model model){

        categoryService.updateCategory(uCategory);
        return "redirect:/admin/categories";

    }
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }
}
