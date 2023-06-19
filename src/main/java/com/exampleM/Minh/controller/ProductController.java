package com.exampleM.Minh.controller;

import com.exampleM.Minh.entity.Product;
import com.exampleM.Minh.services.ProductService;
import com.exampleM.Minh.services.CategoryService;
import com.exampleM.Minh.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/products")

public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @GetMapping("/list")
    public String showAllproducts(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products",products);
        return "admin/product/list";
    }
    @GetMapping("/add")
    public String addproductForm(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("users", userService.getClass());
        return "admin/product/add";
    }

    @PostMapping("/add")
    public String addproduct(@Valid @ModelAttribute("product") Product product, @RequestParam MultipartFile imageProduct, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("categories",categoryService.getAllCategories());
            return "admin/product/add";
        }
        if(imageProduct!=null&&imageProduct.getSize()>0)
        {
            try{
                File saveFile=new ClassPathResource("static/images").getFile();
                String newImageFile= UUID.randomUUID()+".png";
                Path path= Paths.get(saveFile.getAbsolutePath()+File.separator+newImageFile);
                Files.copy(imageProduct.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
                product.setImage(newImageFile);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        productService.addProduct(product);
        return "redirect:/admin/products/list";
    }

    @GetMapping("/edit/{id}")
    public String editproductForm(@PathVariable("id") Long id, Model model){
        Product editproduct = productService.getProductById(id).get();
        if (editproduct!=null){
            model.addAttribute("product", editproduct);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "admin/product/edit";
        } else {
            return "not-found";
        }
    }

    @PostMapping("/edit")
    public  String editproduct( @ModelAttribute("product")Product uproduct,@RequestParam MultipartFile imageProduct,Model model){
        
        if(imageProduct != null && imageProduct.getSize()>0)
        {
            try{
                File saveFile=new ClassPathResource("static/images").getFile();
                String newImageFile= UUID.randomUUID()+".png";
                Path path= Paths.get(saveFile.getAbsolutePath()+File.separator+newImageFile);
                Files.copy(imageProduct.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
                uproduct.setImage(newImageFile);
                
                
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            productService.updateProduct(uproduct);
        }
        
        return "redirect:/admin/products/list";

    }



    @GetMapping("/delete/{id}")
    public String deleteproduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return "redirect:/admin/products/list";
    }

}