package com.oocl.springsecuritydemo.repository;

import com.oocl.springsecuritydemo.entities.Role;
import com.oocl.springsecuritydemo.entities.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {


    @Override
    List<User> findAll();

    @Override
    User save(User user);


    @Query(value = "select * from user_role u, role r where u.user_id = ?1 and u.role_id = r.role_id",
            nativeQuery = true)
    List<Role> findRoleByUserId(String userId);

    @Override
    Optional<User> findById(String s);
}
