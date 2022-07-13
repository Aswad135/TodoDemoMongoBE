package com.mongodemo.tododemo.controller.implemetations;

import com.mongodemo.tododemo.controller.TodoController;
import com.mongodemo.tododemo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoControllerImpl implements TodoController {

    TodoService todoService;

    @Autowired
    public TodoControllerImpl(TodoService todoService) {
        this.todoService = todoService;
    }

    @Override
    public ResponseEntity<Object> getListOfTodos(String listHash) {
        if (listHash==null||listHash.isEmpty())
            return ResponseEntity.badRequest().build();
        return todoService.getListOfTodos(listHash);
    }
}
