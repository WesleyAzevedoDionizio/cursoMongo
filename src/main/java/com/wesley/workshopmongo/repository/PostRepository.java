package com.wesley.workshopmongo.repository;

import com.wesley.workshopmongo.domain.Post;
import com.wesley.workshopmongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {


}
