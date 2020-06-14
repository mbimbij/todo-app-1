package com.example.todoapp.core;

public class ItemPresentation {
    private final String name;
    private final String state;

    private ItemPresentation(String name, String state) {

        this.name = name;
        this.state = state;
    }

    public static ItemPresentation create(String name, String state) {
        return new ItemPresentation(name, state);
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public static ItemPresentation createFromItem(Item item){
        return new ItemPresentation(item.getName(), item.getState());
    }

    @Override
    public String toString() {
        return "ItemPresentation{" +
                "name='" + name + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
