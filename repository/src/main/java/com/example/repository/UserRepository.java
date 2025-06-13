package com.example.repository;

import com.example.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByHandle(String handle);
    
    @Transactional
    @Modifying
    @Query("INSERT INTO User (fullName, handle, message) VALUES (:fullName, :handle, :message)")
    int createUser(
        @Param("fullName") String fullName,
        @Param("handle") String handle,
        @Param("message") String message
    );

    @Transactional
    @Modifying
    @Query("DELETE FROM User WHERE handle = :handle")
    int deleteUserByHandle(@Param("handle") String handle);
} 