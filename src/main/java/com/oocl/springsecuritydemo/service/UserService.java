package com.oocl.springsecuritydemo.service;

import com.oocl.springsecuritydemo.entities.User;
import com.oocl.springsecuritydemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean addUser(User user){
        String userId = UUID.randomUUID().toString().replaceAll("-","");
        user.setUserId(userId);
        if(userRepository.save(user) != null)return true;
        return false;
    }
}
