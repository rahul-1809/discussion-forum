package com.example.companyforum.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data // Lombok annotation to create getters, setters, toString, etc.
@Entity // Marks this class as a JPA entity (a table in the DB)
@Table(name = "users") // Specifies the table name in the database
public class User {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments the ID
    private Long id;

    @Column(nullable = false, unique = true) // Cannot be null and must be unique
    private String username;

    @Column(nullable = false)
    private String password;

    // A user can have many posts
    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    // A user can have many comments
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
}