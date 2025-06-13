package com.example.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Full name must be less than 100 characters")
    private String fullName;

    @NotBlank(message = "Handle is required")
    @Size(max = 50, message = "Handle must be less than 50 characters")
    private String handle;

    @Size(max = 1000, message = "Message must be less than 1000 characters")
    private String message;
} 