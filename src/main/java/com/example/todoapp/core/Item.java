package com.example.todoapp.core;

import lombok.Value;

import java.util.UUID;

@Value
public class Item {
    private final String id;
    private final String name;
    private final String owner;
    private final String state;

    public static Item create(String name, String owner, String state) {
        return new Item(UUID.randomUUID().toString(), name, owner, state);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
