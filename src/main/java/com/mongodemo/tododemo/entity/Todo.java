package com.mongodemo.tododemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    private int id;
    private String contents;
    private boolean isDone;
    private String createdOn = "";
    private String modifiedOn = "";
}
