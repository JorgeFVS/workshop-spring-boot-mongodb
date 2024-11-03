package com.jorge.workshopmongo.services;

import com.jorge.workshopmongo.domain.Post;
import com.jorge.workshopmongo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    public PostRepository postRepository;

    public Post findById(String id) {
        Optional<Post> posts = postRepository.findById(id);
        return posts.orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public List<Post> findByTitle(String text) {
        return postRepository.findByTitle(text);
    }
}
