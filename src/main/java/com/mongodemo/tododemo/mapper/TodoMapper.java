package com.mongodemo.tododemo.mapper;

import com.mongodemo.tododemo.dto.TodoDTO;
import com.mongodemo.tododemo.entity.Todo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class TodoMapper {

    public static TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);

    public abstract TodoDTO toDTO(Todo todo);

    @InheritInverseConfiguration(name = "toDTO")
    public abstract Todo fromDTO(TodoDTO todoDTO);
}
