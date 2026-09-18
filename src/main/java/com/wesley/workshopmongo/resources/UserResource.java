package com.wesley.workshopmongo.resources;

import com.wesley.workshopmongo.domain.Post;
import com.wesley.workshopmongo.domain.User;
import com.wesley.workshopmongo.dto.UserDTO;
import com.wesley.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @Autowired
    UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = new ArrayList<>();
        list = service.findAll();
        List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok(listDTO);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        User obj = service.findById(id);
        return ResponseEntity.ok(new UserDTO(obj));
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
        User obj = service.findById(id);
        return ResponseEntity.ok(obj.getPosts());
    }


    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO obj){
           User u = service.fromDTO(obj);
           service.insert(u);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(u.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody UserDTO obj){
        User u = service.fromDTO(obj);
        u.setId(id);
        service.update(u);
        return ResponseEntity.noContent().build();
    }


}
