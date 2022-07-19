package com.mongodemo.tododemo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("TODOAPPPOSTGRESCLIENT")
public interface TodoAppPostgresClient {

    @GetMapping("/todoList/listHash")
    ResponseEntity<Object> getTodoListByListHash(@RequestParam String listHash);
}
