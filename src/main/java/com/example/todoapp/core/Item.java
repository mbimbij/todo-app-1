package com.example.todoapp.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Item {
    private String id;
    private String name;
    private String owner;
    private String state;

    public static Item create(String name, String owner, String state) {
        return new Item(UUID.randomUUID().toString(), name, owner, state);
    }

    public Item copy() {
        return new Item(id, name, owner, state);
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
