package com.exampleM.Minh.services;

import com.exampleM.Minh.entity.User;
import com.exampleM.Minh.repository.IRoleRepository;
import com.exampleM.Minh.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    public void save(User user)
    {
        userRepository.save(user);
        Long userId=userRepository.getUserIByUsername(user.getUsername());
        Long roleId=roleRepository.getRoleIdByName("USER");
        if(roleId !=0 && userId !=0)
        {

            userRepository.addRoleToUser(userId,roleId);
        }

    }
}
