package com.mongodemo.tododemo.mapper;

import com.mongodemo.tododemo.dto.TodoListDTO;
import com.mongodemo.tododemo.entity.TodoList;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class TodoListMapper {

    public static TodoListMapper INSTANCE = Mappers.getMapper(TodoListMapper.class);

    public abstract TodoListDTO toDTO(TodoList todo);

    @InheritInverseConfiguration(name = "toDTO")
    public abstract TodoList fromDTO(TodoListDTO todoListDTO);
}
