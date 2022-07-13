package com.mongodemo.tododemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface TodoController {

    @GetMapping(value = "/list")
    public ResponseEntity<Object> getListOfTodos(@RequestParam String listHash);

}
