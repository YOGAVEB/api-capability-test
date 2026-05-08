package com.example.service.b.repository;

import com.example.service.b.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}