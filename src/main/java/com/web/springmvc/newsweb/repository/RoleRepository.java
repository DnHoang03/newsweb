package com.web.springmvc.newsweb.repository;

import com.web.springmvc.newsweb.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByCode(String user);
}
