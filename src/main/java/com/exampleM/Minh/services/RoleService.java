package com.exampleM.Minh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exampleM.Minh.entity.Role;
import com.exampleM.Minh.repository.IRoleRepository;
import java.util.List;
import java.util.Optional;
@Service
public class RoleService {
    @Autowired
    private IRoleRepository roleRepository;

    
    public List<Role> getAllRoles() { return roleRepository.findAll(); }
    public Role getRoleById(Long id){
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()){
            return optionalRole.get();
        }else {
            throw new RuntimeException("Category not found");
        }
    }
    public void addRole(Role role){
        roleRepository.save(role);
    }
    public void updateRole(Role role){
        roleRepository.save(role);
    }
    public Role saveRole(Role role){
        return roleRepository.save(role);
    }

    public void deleteRole(Long id){roleRepository.deleteById(id);}
}
