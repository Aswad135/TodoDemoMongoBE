package com.mongodemo.tododemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodemo.tododemo.commons.RestClient;
import com.mongodemo.tododemo.entity.Todo;
import com.mongodemo.tododemo.entity.TodoList;
import com.mongodemo.tododemo.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class TodoService {

    TodoRepository todoRepository;
    RestClient restClient = new RestClient();
    Logger logger = LoggerFactory.getLogger(TodoService.class);

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public ResponseEntity<Object> getListOfTodos(String listHash) {

        try {
            TodoList todoList = todoRepository.findTodoListByListHash(listHash);
            if (todoList != null)
                return new ResponseEntity<>(todoList, HttpStatus.OK);
            else {
                todoList = getTodoFromServer(listHash);
                todoList = todoRepository.save(todoList);
                return new ResponseEntity<>(todoList, HttpStatus.OK);
            }
        } catch (Exception ignored) {
            logger.error(ignored.getMessage());
        }
        return new ResponseEntity<>("No Todo Found with this Hash", HttpStatus.BAD_REQUEST);
    }

    private TodoList getTodoFromServer(String listHash) {

        String Response = this.restClient.get("/todoList/listHash?listHash=" + listHash);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            TodoList todoList = objectMapper.readValue(Response, TodoList.class);
            return todoList;
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
        return null;

    }
}
