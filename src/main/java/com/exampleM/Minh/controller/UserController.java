package com.exampleM.Minh.controller;

import com.exampleM.Minh.entity.User;
import com.exampleM.Minh.services.RoleService;
import com.exampleM.Minh.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


    @Controller
    public class UserController {
        @Autowired
        private UserService userService;
        // @Autowired
        // private RoleService roleService;
    
        @GetMapping("/login")
        public String login() {
            return "user/login";
        }
        @GetMapping("/register")
        public String register(Model model) {
            model.addAttribute("user", new User());
            return "user/register";
        }
        @PostMapping("/register")
        public String register(@Valid @ModelAttribute("user") User user,
                               BindingResult bindingResult, Model model) {
            if (bindingResult.hasErrors()) {
                bindingResult.getFieldErrors().forEach(error->model.addAttribute(error.getField()+"_error",error.getDefaultMessage()));
                return "user/register";
            }
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userService.save(user);

            return "redirect:/login";
        }
        // @GetMapping("/edit/{id}")
        // public String editUserForm(@PathVariable("id") Long id,Model model){
        //     User editUser=userService.getuserById(id);
        //     if (editUser!=null){
        //         model.addAttribute("user", editUser);
        //         model.addAttribute("Role", roleService.getAllRoles() );
    
        //         return "user/edit";
        //     } else {
        //         return "not-found";
        //     }
        // }
    }
