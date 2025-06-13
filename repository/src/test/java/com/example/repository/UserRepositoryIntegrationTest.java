package com.example.repository;

import com.example.common.entity.User;
import com.example.repository.config.BaseRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserRepositoryIntegrationTest extends BaseRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldCreateAndFindUserByHandle() {
        // Given
        String fullName = "John Doe";
        String handle = "johndoe";
        String message = "Hello, world!";

        // When
        int rowsAffected = userRepository.createUser(fullName, handle, message);
        Optional<User> foundUser = userRepository.findByHandle(handle);

        // Then
        assertThat(rowsAffected).isEqualTo(1);

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getFullName()).isEqualTo(fullName);
        assertThat(foundUser.get().getHandle()).isEqualTo(handle);
        assertThat(foundUser.get().getMessage()).isEqualTo(message);

        userRepository.deleteUserByHandle(handle);

        Optional<User> deletedUser = userRepository.findByHandle(handle);
        assertThat(deletedUser).isEmpty();
    }

    @Test
    void shouldNotFindNonExistentUser() {
        // When
        Optional<User> user = userRepository.findByHandle("nonexistent");

        // Then
        assertThat(user).isEmpty();
    }

    @Test
    void shouldNotCreateUserWithDuplicateHandle() {
        // Given
        String handle = "johndoe";
        int rowsAffected = userRepository.createUser("John Doe", handle, "First user");
        assertThat(rowsAffected).isEqualTo(1);

        // When/Then
        assertThatThrownBy(() -> 
            userRepository.createUser("Jane Doe", handle, "Second user")
        ).isInstanceOf(Exception.class); // This will be a database constraint violation

        userRepository.deleteUserByHandle(handle);

        Optional<User> deletedUser = userRepository.findByHandle(handle);
        assertThat(deletedUser).isEmpty();
    }

    @Test
    void shouldCreateMultipleUsersWithDifferentHandles() {
        // Given
        int rowsAffected1 = userRepository.createUser("John Doe", "johndoe", "First user");
        assertThat(rowsAffected1).isEqualTo(1);
        int rowsAffected2 = userRepository.createUser("Jane Doe", "janedoe", "Second user");
        assertThat(rowsAffected2).isEqualTo(1);

        // When
        Optional<User> foundUser1 = userRepository.findByHandle("johndoe");
        Optional<User> foundUser2 = userRepository.findByHandle("janedoe");

        // Then
        assertThat(foundUser1).isPresent();
        assertThat(foundUser2).isPresent();
        assertThat(foundUser1.get().getId()).isNotEqualTo(foundUser2.get().getId());
        assertThat(foundUser1.get().getHandle()).isNotEqualTo(foundUser2.get().getHandle());

        userRepository.deleteUserByHandle("johndoe");
        userRepository.deleteUserByHandle("janedoe");

        Optional<User> deletedUser1 = userRepository.findByHandle("johndoe");
        Optional<User> deletedUser2 = userRepository.findByHandle("janedoe");
        assertThat(deletedUser1).isEmpty();
        assertThat(deletedUser2).isEmpty();
    }
} 