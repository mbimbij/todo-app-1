package com.example.todoapp.cucumber;

import com.example.todoapp.core.Item;
import com.example.todoapp.core.ItemRepository;
import io.cucumber.java.en.And;

import java.util.List;

public class BackgroupStepDef {
    private ItemRepository itemRepository;

    public BackgroupStepDef(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @And("the todo items")
    public void theTodoItems(List<Item> items) {
        itemRepository.deleteAll();
        items.forEach(itemRepository::save);
    }
}
