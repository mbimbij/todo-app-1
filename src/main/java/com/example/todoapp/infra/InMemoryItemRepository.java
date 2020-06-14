package com.example.todoapp.infra;

import com.example.todoapp.core.Item;
import com.example.todoapp.core.ItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryItemRepository implements ItemRepository {

    private List<Item> items = new ArrayList<>();

    @Override
    public void save(Item item) {
        items.add(item);
    }

    @Override
    public List<Item> getItemsForUser(String user) {
        return items.stream()
                .filter(item -> item.getOwner().equals(user))
                .collect(Collectors.toList());
    }
}
