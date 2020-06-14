package com.example.todoapp.cucumber;

import com.example.todoapp.core.Item;
import com.example.todoapp.core.ItemPresentation;
import com.example.todoapp.core.User;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;

import java.util.Map;

public class TypeConverters {
    @DataTableType
    public Item item(Map<String, String> map) {
        return Item.create(map.get("name"), map.get("owner"), map.get("state"));
    }

    @DataTableType
    public ItemPresentation itemPresentation(Map<String, String> map) {
        return ItemPresentation.create(map.get("name"), map.get("state"));
    }

    @ParameterType(".*")
    public User user(String userId){
        return User.createWithId(userId);
    }
}
