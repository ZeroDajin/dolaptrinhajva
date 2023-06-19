package com.exampleM.Minh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exampleM.Minh.entity.Role;
import com.exampleM.Minh.services.RoleService;

@Controller
@RequestMapping("/admin/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping
    public String showAllRoles(Model model){
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles",roles);
        return "admin/role/list";
    }
    
}
