package com.example.companyforum.repositories;

import com.example.companyforum.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// JpaRepository<EntityType, PrimaryKeyType>
public interface UserRepository extends JpaRepository<User, Long> {

    // Spring Data JPA automatically creates the query for us
    // based on the method name "findByUsername".
    Optional<User> findByUsername(String username);
}