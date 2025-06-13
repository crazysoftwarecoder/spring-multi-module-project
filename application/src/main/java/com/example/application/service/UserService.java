package com.example.application.service;

import com.example.common.dto.CreateUserRequest;
import com.example.common.entity.User;

public interface UserService {
    /**
     * Creates a new user with the provided details
     * @param request the user creation request
     * @return the created user
     */
    User createUser(CreateUserRequest request);

    /**
     * Finds a user by their handle
     * @param handle the user's handle
     * @return the user if found
     * @throws jakarta.persistence.EntityNotFoundException if user not found
     */
    User getUserByHandle(String handle);
} 