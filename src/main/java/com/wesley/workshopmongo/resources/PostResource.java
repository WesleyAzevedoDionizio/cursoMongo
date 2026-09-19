package com.wesley.workshopmongo.resources;

import com.wesley.workshopmongo.domain.Post;
import com.wesley.workshopmongo.resources.util.URL;
import com.wesley.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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

    @GetMapping(value = "/titleSearch")
    public ResponseEntity<List<Post>>  findByTitle(
            @RequestParam(value = "text", defaultValue = "") String text){
        text = URL.decodeParam(text);
        return ResponseEntity.ok().body(postService.findByTitle(text));
    }

    @GetMapping(value = "/fullSearch")
    public ResponseEntity<List<Post>>  fullSearch(@RequestParam(value = "text", defaultValue = "") String text,
                                                  @RequestParam(value = "minDate", defaultValue = "") String minDate,
                                                  @RequestParam(value = "maxDate", defaultValue = "") String maxDate){
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date(0L));
        return ResponseEntity.ok().body(postService.fullSearch(text, min, max));
    }
}
