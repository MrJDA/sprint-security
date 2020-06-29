package com.oocl.springsecuritydemo;

import com.oocl.springsecuritydemo.entities.Permission;
import com.oocl.springsecuritydemo.entities.Role;
import com.oocl.springsecuritydemo.entities.User;
import com.oocl.springsecuritydemo.repository.RoleRepository;
import com.oocl.springsecuritydemo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class SpringSecurityDemoApplicationTests {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void addRole(){
        Role role = new Role(UUID.randomUUID().toString().replaceAll("-",""), "CUSTOMER");
        List<Permission> permissions = new ArrayList<>();
//        Permission permission1 = new Permission(UUID.randomUUID().toString().replaceAll("-",""), "WRITE");
        Permission permission2 = new Permission(UUID.randomUUID().toString().replaceAll("-",""), "READ");
//        Permission permission3 = new Permission(UUID.randomUUID().toString().replaceAll("-",""), "DELETE");
//        permissions.add(permission1);
        permissions.add(permission2);
//        permissions.add(permission3);
        role.setPermissions(permissions);
        roleRepository.save(role);
    }


    @Test
    void addUser(){
        User user = new User(UUID.randomUUID().toString().replaceAll("-",""),"kate",new BCryptPasswordEncoder().encode("123456"));
        List<Role> roles = new ArrayList<>();
        Role role1 = new Role("943b7c3b8dc84046b637e0597590c842", "CUSTOMER");
        roles.add(role1);
        user.setRoles(roles);
        userRepository.save(user);
    }

}
