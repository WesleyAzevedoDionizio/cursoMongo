package com.wesley.workshopmongo.services;

import com.wesley.workshopmongo.domain.User;
import com.wesley.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repo;

    public List<User> findAll (){
        return repo.findAll();
    }

}
