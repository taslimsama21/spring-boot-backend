package com.taslim.springbootbackend.repository;

import com.taslim.springbootbackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
