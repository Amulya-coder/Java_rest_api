package com.example.springbootmongodb.repository;

import java.util.List;

import com.example.springbootmongodb.model.Server;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServerRepository extends MongoRepository<Server, Long> {
    public List<Server> findByName(String name);

}
