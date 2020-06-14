package com.example.todoapp.infra;

import lombok.Data;

@Data
public class CreateItemInputRest {
    private final String name;
    private final String state;
}
