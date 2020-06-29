package com.oocl.springsecuritydemo.repository;

import com.oocl.springsecuritydemo.entities.Permission;
import com.oocl.springsecuritydemo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {

    @Override
    Role save(Role role);

    @Override
    Optional<Role> findById(String s);

    @Query(value = "select * from permission p, role_permission r where r.role_id = ?1 and  r.permission_id = p.permission_id"
            , nativeQuery = true)
    List<Permission> findPermissionsByRoleId(String roleId);
}
