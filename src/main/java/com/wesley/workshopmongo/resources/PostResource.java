package com.wesley.workshopmongo.resources;

import com.wesley.workshopmongo.domain.Post;
import com.wesley.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    PostService postService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post>  findByid(@PathVariable String id){
        Post p1 = postService.findById(id);
        return ResponseEntity.ok().body(p1);
    }

}
