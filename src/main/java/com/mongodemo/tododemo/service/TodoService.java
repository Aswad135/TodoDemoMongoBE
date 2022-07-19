package com.mongodemo.tododemo.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.mongodemo.tododemo.commons.RestClient;
import com.mongodemo.tododemo.dto.TodoListDTO;
import com.mongodemo.tododemo.entity.Todo;
import com.mongodemo.tododemo.entity.TodoList;
import com.mongodemo.tododemo.feign.TodoAppPostgresClient;
import com.mongodemo.tododemo.mapper.TodoListMapper;
import com.mongodemo.tododemo.mapper.TodoMapper;
import com.mongodemo.tododemo.repository.TodoRepository;
import jdk.nashorn.internal.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class TodoService {

    TodoRepository todoRepository;
    Logger logger = LoggerFactory.getLogger(TodoService.class);

    TodoAppPostgresClient postgresClient;

    @Autowired
    public TodoService(TodoRepository todoRepository, TodoAppPostgresClient postgresClient) {
        this.todoRepository = todoRepository;
        this.postgresClient = postgresClient;
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
        ResponseEntity<Object> Response = this.postgresClient.getTodoListByListHash(listHash);
        if (Response.getStatusCode() == HttpStatus.OK) {
            ObjectMapper mapper = new ObjectMapper()
                    .registerModule(new ParameterNamesModule())
                    .registerModule(new Jdk8Module())
                    .registerModule(new JavaTimeModule());
            TodoListDTO todoListDTO;
            try {
                String jsonString = new ObjectMapper().writeValueAsString(Response.getBody());
                todoListDTO = mapper.readValue(jsonString, TodoListDTO.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return TodoListMapper.INSTANCE.fromDTO(todoListDTO);
        }
        return null;
    }
}