package com.example.companyforum.repositories;

import com.example.companyforum.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // This method will find all posts and order them by creation date,
    // with the newest posts first.
    List<Post> findAllByOrderByCreatedAtDesc();
}