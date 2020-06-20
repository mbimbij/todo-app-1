package com.example.todoapp.infra.itemrepository;

import com.example.todoapp.core.Item;
import com.example.todoapp.core.ItemRepository;
import com.example.todoapp.core.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

    @Override
    public void deleteByIds(Collection<String> itemIds) {
        items.removeIf(item -> itemIds.contains(item.getId()));
    }

    @Override
    public void deleteById(String itemId) {
        items.removeIf(item -> Objects.equals(item.getId(), itemId));
    }

    @Override
    public boolean exists(String itemId) {
        return items.stream().anyMatch(item -> Objects.equals(item.getId(), itemId));
    }

    @Override
    public Optional<Item> getById(String itemId) {
        return items.stream()
                .filter(item -> Objects.equals(item.getId(), itemId))
                .findFirst();
    }

    @Override
    public Item upsert(Item updatedItem) {
        deleteById(updatedItem.getId());
        save(updatedItem);
        return updatedItem;
    }
}
