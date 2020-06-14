package com.example.todoapp.core;

import java.util.UUID;

public class Item {
    private final String id;
    private final String name;
    private final String owner;
    private final String state;

    private Item(String id, String name, String owner, String state) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.state = state;
    }

    public static Item create(String name, String owner, String state) {
        return new Item(UUID.randomUUID().toString(), name, owner, state);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getState() {
        return state;
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
