package com.oocl.springsecuritydemo.service;

import com.oocl.springsecuritydemo.entities.User;
import com.oocl.springsecuritydemo.entities.UserDetail;
import com.oocl.springsecuritydemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//用于从数据库里面拿数据，构建userDetail对象
@Service
public class UserDetailService  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findById(s);
        if(!optionalUser.isPresent())return null;
        User user = optionalUser.get();
        UserDetail userDetail = new UserDetail();
        userDetail.setPassword(user.getPassword());
        userDetail.setRoles(user.getRoles());
        userDetail.setUserId(user.getUserId());
        userDetail.setUserName(user.getUserName());
        return userDetail;
    }
}
