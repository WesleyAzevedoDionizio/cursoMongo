package com.wesley.workshopmongo.services;

import com.wesley.workshopmongo.domain.User;
import com.wesley.workshopmongo.dto.UserDTO;
import com.wesley.workshopmongo.repository.UserRepository;
import com.wesley.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repo;

    public List<User> findAll (){
        return repo.findAll();
    }

    public User  findById(String id) {
        Optional<User> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insert (User obj){return repo.save(obj);}

    public void delete (String id){
        findById(id);
        repo.deleteById(id);
    }

    public User update(User obj){
        User user = repo.findById(obj.getId()).orElseThrow(()
                -> new ObjectNotFoundException("objeto nao encontrado"));
        updateData(user, obj);
        return repo.save(user);
    }

    public void updateData(User user, User obj){
        user.setName(obj.getName());
        user.setEmail(obj.getEmail());

    }

    public User fromDTO(UserDTO objDTO){
        return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
    }
}
