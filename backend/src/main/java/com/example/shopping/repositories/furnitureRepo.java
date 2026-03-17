package com.example.shopping.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.shopping.model.furniture;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface furnitureRepo extends MongoRepository<furniture, String>{


    // Custom delete method can be declared here if needed, but implementation should be in a class


}
