package com.example.shopping.repositories;

import com.example.shopping.model.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends MongoRepository<user, String> {
    boolean existsByUsername(String username);
    user findByUsername(String username);


}

