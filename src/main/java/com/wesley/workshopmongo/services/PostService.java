package com.wesley.workshopmongo.services;

import com.wesley.workshopmongo.domain.Post;
import com.wesley.workshopmongo.domain.User;
import com.wesley.workshopmongo.repository.PostRepository;
import com.wesley.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public Post findById(String id) {
        Optional<Post> obj = postRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text){
        return postRepository.findByTitleContaining(text);
    }
}
