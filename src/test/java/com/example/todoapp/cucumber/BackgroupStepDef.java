package com.example.todoapp.cucumber;

import com.example.todoapp.core.Item;
import com.example.todoapp.core.ItemRepository;
import com.example.todoapp.core.User;
import com.example.todoapp.core.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import java.util.List;

public class BackgroupStepDef {
    private ItemRepository itemRepository;
    private UserRepository userRepository;

    public BackgroupStepDef(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Given("users")
    public void loggedInUsers(List<String> userIds) {
        userIds.stream().map(User::createWithId).forEach(userRepository::save);
    }

    @And("the todo items")
    public void theTodoItems(List<Item> items) {
        itemRepository.deleteAll();
        items.forEach(itemRepository::save);
    }
}
