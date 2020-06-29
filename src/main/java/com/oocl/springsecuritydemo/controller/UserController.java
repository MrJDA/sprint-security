package com.oocl.springsecuritydemo.controller;

import com.oocl.springsecuritydemo.entities.User;
//import org.springframework.security.access.prepost.PreAuthorize;
import com.oocl.springsecuritydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.*;

@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users-information")
    //用于对接口指定授权
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<User> getAllUserInfo(){
        return Arrays.asList(new User[]{new User(UUID.randomUUID().toString(),"Jaylon","123")});
    }

    @PostMapping("/user-information")
    public ResponseEntity<String> addUser(@RequestBody User user){
        ResponseEntity<String> responseEntity = null;
        if(userService.addUser(user)){
            responseEntity = new ResponseEntity<>("Create a new count",HttpStatus.OK);
        }else{
            responseEntity = new ResponseEntity<>("Create new count fail", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}
