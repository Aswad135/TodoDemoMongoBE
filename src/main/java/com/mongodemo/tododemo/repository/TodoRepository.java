package com.mongodemo.tododemo.repository;

import com.mongodemo.tododemo.entity.TodoList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends MongoRepository<TodoList, Integer> {

    TodoList findTodoListByListHash(String listHash);
}
