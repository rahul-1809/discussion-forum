package com.example.companyforum.repositories;

import com.example.companyforum.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // The default methods like save(), findById(), etc. are enough for now.
}