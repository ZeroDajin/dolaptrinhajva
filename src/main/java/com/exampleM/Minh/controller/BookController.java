package com.exampleM.Minh.controller;

import com.exampleM.Minh.entity.Book;
import com.exampleM.Minh.services.BookService;
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
@RequestMapping("/admin/books")

public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @GetMapping
    public String showAllBooks(Model model){
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books",books);
        return "admin/book/list";
    }
    @GetMapping("/add")
    public String addBookForm(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("users", userService.getClass());
        return "admin/book/add";
    }

    @PostMapping("/add")
    public String addBook(@Valid @  ModelAttribute("book") Book book, @RequestParam MultipartFile imageProduct, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("categories",categoryService.getAllCategories());
            return "admin/book/add";
        }
        if(imageProduct!=null&&imageProduct.getSize()>0)
        {
            try{
                File saveFile=new ClassPathResource("static/images").getFile();
                String newImageFile= UUID.randomUUID()+".png";
                Path path= Paths.get(saveFile.getAbsolutePath()+File.separator+newImageFile);
                Files.copy(imageProduct.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
                book.setImage(newImageFile);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        bookService.addBook(book);
        return "redirect:/admin/books";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id, Model model){
        Book editBook = bookService.getBookById(id);
        if (editBook!=null){
            model.addAttribute("book", editBook);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "admin/book/edit";
        } else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public  String editBook( @ModelAttribute("book")Book uBook,@RequestParam MultipartFile imageProduct,Model model){
        if(imageProduct != null && imageProduct.getSize()>0)
        {
            try{
                File saveFile = new ClassPathResource("static/images").getFile();
                Path path=Paths.get(saveFile.getAbsolutePath() +File.separator + uBook.getImage());
                Files.copy(imageProduct.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        bookService.updateBook(uBook);
        return "redirect:/admin/books";

    }



    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        bookService.deleteBook(id);
        return "redirect:/admin/books";
    }



}