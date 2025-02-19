package com.evento.app.application.service;

import com.evento.app.application.exceptions.UserAlreadyExistsException;
import com.evento.app.application.exceptions.UserNotFoundException;
import com.evento.app.domain.entity.Role;
import com.evento.app.domain.entity.User;
import com.evento.app.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Set<Role> getAllUserRoles(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return user.getRoles();
    }

    public void registerUser(User user) {
        final User existingUser = userRepository.findByUsername(user.getUsername()).orElse(null);
        if (existingUser != null) {
            throw new UserAlreadyExistsException("User with username " + user.getUsername() + " already exists");
        }

        userRepository.save(user);
    }

}
