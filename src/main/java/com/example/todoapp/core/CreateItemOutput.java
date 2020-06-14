package com.example.todoapp.core;

import lombok.Data;

@Data
public class CreateItemOutput {
    private final String name;
    private final String state;

    public static CreateItemOutput createFromItem(Item newItem) {
        return new CreateItemOutput(newItem.getName(), newItem.getState());
    }
}
