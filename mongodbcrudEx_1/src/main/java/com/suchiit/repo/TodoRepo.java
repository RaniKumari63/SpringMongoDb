package com.suchiit.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.suchiit.model.TodoDTO;
@Repository
public interface TodoRepo extends MongoRepository<TodoDTO, String> {

}
