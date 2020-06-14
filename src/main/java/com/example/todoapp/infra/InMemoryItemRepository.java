package com.example.todoapp.infra;

import com.example.todoapp.core.Item;
import com.example.todoapp.core.ItemRepository;
import com.example.todoapp.core.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InMemoryItemRepository implements ItemRepository {

    private List<Item> items = new ArrayList<>();

    @Override
    public Item save(Item item) {
        items.add(item);
        return item;
    }

    @Override
    public void deleteAll() {
        items.clear();
    }

    @Override
    public List<Item> getItemsForUser(String user) {
        return items.stream()
                .filter(item -> item.getOwner().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllForUser(User user) {
        items.removeIf(item -> Objects.equals(item.getOwner(), user.getId()));
    }
}
