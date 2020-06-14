package com.example.todoapp.infra;

import lombok.Data;

@Data
public class CreateItemOutputRest {
    private final String name;
    private final String state;
}
