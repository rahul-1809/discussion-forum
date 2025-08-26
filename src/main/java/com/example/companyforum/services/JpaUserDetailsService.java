package com.example.companyforum.services;

import com.example.companyforum.models.User;
import com.example.companyforum.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service // Marks this as a Spring service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // Inject our UserRepository to find users
    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // This is the method Spring Security will call when a user tries to log in
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find the user from the database
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Convert our User object into a UserDetails object that Spring Security understands
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>() // For simplicity, we are not using roles for now
        );
    }
}