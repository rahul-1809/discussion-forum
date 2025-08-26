package com.example.companyforum.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String companyName;

    @Lob // Specifies that this can be a large object (long text)
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Many posts can belong to one user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Defines the foreign key column
    private User user;

    // One post can have many comments
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL) // CascadeType.ALL means if a post is deleted, its comments are too
    private List<Comment> comments;
}