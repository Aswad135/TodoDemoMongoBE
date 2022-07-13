package com.mongodemo.tododemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class TodoList {

    @Id
    private int id;
    private String title = "";
    private String listHash;
    private List<Todo> ListOfTodos = new ArrayList<>();
}
