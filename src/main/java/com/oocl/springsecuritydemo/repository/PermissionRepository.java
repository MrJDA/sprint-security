package com.oocl.springsecuritydemo.repository;

import com.oocl.springsecuritydemo.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, String> {

    @Override
    Permission save(Permission permission);


    @Override
    Optional<Permission> findById(String s);

}
