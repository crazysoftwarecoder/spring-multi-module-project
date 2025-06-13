package com.example.application.service;

import com.example.common.dto.CreateUserRequest;
import com.example.common.entity.User;
import com.example.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User createUser(CreateUserRequest request) {
        // Create the user using the repository
        userRepository.createUser(
            request.getFullName(),
            request.getHandle(),
            request.getMessage()
        );

        // Fetch and return the created user
        return userRepository.findByHandle(request.getHandle())
            .orElseThrow(() -> new IllegalStateException("Failed to create user"));
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByHandle(String handle) {
        return userRepository.findByHandle(handle)
            .orElseThrow(() -> new EntityNotFoundException("User not found with handle: " + handle));
    }
} 