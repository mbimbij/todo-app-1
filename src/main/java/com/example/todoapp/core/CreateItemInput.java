package com.example.todoapp.core;

public class CreateItemInput {
    private final String userId;
    private final String name;
    private final String state;

    public CreateItemInput(String userId, String name, String state) {

        this.userId = userId;
        this.name = name;
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }
}
