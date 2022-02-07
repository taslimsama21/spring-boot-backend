package com.taslim.springbootbackend.repository;

import com.taslim.springbootbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

   /* @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findUserByEmail(String email);*/

    Optional<User> findUserByEmail(String email);
    Boolean existsByEmail(String email);
}
