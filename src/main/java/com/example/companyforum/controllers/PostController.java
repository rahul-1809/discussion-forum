package com.example.companyforum.controllers;

import com.example.companyforum.models.Comment;
import com.example.companyforum.models.Post;
import com.example.companyforum.models.User;
import com.example.companyforum.repositories.CommentRepository;
import com.example.companyforum.repositories.PostRepository;
import com.example.companyforum.repositories.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public PostController(PostRepository postRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("posts", postRepository.findAllByOrderByCreatedAtDesc());
        return "index";
    }

    @GetMapping("/posts/new")
    public String showCreatePostForm(Model model) {
        model.addAttribute("post", new Post());
        return "create-post";
    }

    @PostMapping("/posts")
    public String createPost(@ModelAttribute Post post, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        post.setUser(user);
        postRepository.save(post);
        return "redirect:/";
    }

    @GetMapping("/posts/{id}")
    public String showPostDetails(@PathVariable("id") Long id, Model model) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID:" + id));
        model.addAttribute("post", post);
        // We no longer need to add a "newComment" object to the model for the form
        return "post-details";
    }

    /**
     * This is the updated method to handle adding comments.
     * It manually creates the Comment object to avoid binding errors.
     */
    @PostMapping("/posts/{id}/comments")
    public String addComment(@PathVariable("id") Long postId,
                             @RequestParam("text") String commentText,
                             @AuthenticationPrincipal UserDetails userDetails) {

        // Find the related user and post
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID:" + postId));

        // Manually create a NEW comment object
        Comment newComment = new Comment();
        newComment.setText(commentText);
        newComment.setUser(user);
        newComment.setPost(post);

        // Save the new object
        commentRepository.save(newComment);

        return "redirect:/posts/" + postId;
    }
}