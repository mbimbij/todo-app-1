package com.example.todoapp.core;

import lombok.Value;

@Value
public class ChangeItemStateOutput {
    private final String id;
    private final String name;
    private final String state;

    public static ChangeItemStateOutput createFromItem(Item item){
        return new ChangeItemStateOutput(item.getId(), item.getName(),item.getState());
    }
}
