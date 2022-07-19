package com.mongodemo.tododemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TodoDTO {
    @JsonProperty("contents")
    private String contents;
    @JsonProperty("id")
    private int id;
    @JsonProperty("createdOn")
    private LocalDateTime createdOn;
    @JsonProperty("modifiedOn")
    private LocalDateTime modifiedOn;
    @JsonProperty("done")
    private boolean done;
}
